!function(factory) {
    if (typeof define === 'function' && define.amd) {
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        factory(require('jquery'));
    } else {
        factory(jQuery);
    }
}(function($) {
    var userAgent = navigator.userAgent,
        isiPhone = /iphone/i.test(userAgent),
        isChrome = /chrome/i.test(userAgent),
        isAndroid = /android/i.test(userAgent);

    $.mask = {
        definitions: {
            '9': '[0-9]',
            'a': '[A-Za-z]',
            '*': '[A-Za-z0-9]'
        },
        autoclear: true,
        dataName: 'rawMaskFn',
        placeholder: '_'
    };

    $.fn.extend({
        caret: function(begin, end) {
            if (this.length === 0 || this.is(':hidden')) {
                return;
            }
            if (typeof begin === 'number') {
                end = (typeof end === 'number') ? end : begin;
                return this.each(function() {
                    if (this.setSelectionRange) {
                        this.setSelectionRange(begin, end);
                    } else if (this.createTextRange) {
                        var range = this.createTextRange();
                        range.collapse(true);
                        range.moveEnd('character', end);
                        range.moveStart('character', begin);
                        range.select();
                    }
                });
            } else {
                if (this[0].setSelectionRange) {
                    begin = this[0].selectionStart;
                    end = this[0].selectionEnd;
                } else if (document.selection && document.selection.createRange) {
                    var range = document.selection.createRange();
                    begin = 0 - range.duplicate().moveStart('character', -100000);
                    end = begin + range.text.length;
                }
                return { begin: begin, end: end };
            }
        },
        unmask: function() {
            return this.trigger('unmask');
        },
        mask: function(mask, settings) {
            if (!mask && this.length > 0) {
                return this.trigger('unmask').data($.mask.dataName)();
            }

            settings = $.extend({
                autoclear: $.mask.autoclear,
                placeholder: $.mask.placeholder,
                completed: null
            }, settings);

            var defs = $.mask.definitions;
            var tests = [];
            var partialPosition = mask.length;
            var firstNonMaskPos = null;
            var lastRequiredNonMaskPos = null;

            $.each(mask.split(''), function(i, c) {
                if (c === '?') {
                    partialPosition--;
                } else if (defs[c]) {
                    tests.push(new RegExp(defs[c]));
                    if (firstNonMaskPos === null) {
                        firstNonMaskPos = tests.length - 1;
                    }
                    if (i < partialPosition) {
                        lastRequiredNonMaskPos = tests.length - 1;
                    }
                } else {
                    tests.push(null);
                }
            });

            return this.trigger('unmask').each(function() {
                var input = $(this);
                var buffer = $.map(mask.split(''), function(c, i) {
                    return defs[c] ? settings.placeholder.charAt(i < settings.placeholder.length ? i : 0) : c !== '?' ? c : null;
                });

                function writeBuffer() {
                    input.val(buffer.join(''));
                }

                function checkVal(allow) {
                    var testVal = input.val();
                    var lastMatch = -1;
                    for (var i = 0; i < tests.length; i++) {
                        if (tests[i]) {
                            buffer[i] = settings.placeholder.charAt(i < settings.placeholder.length ? i : 0);
                            while (testVal.charAt(lastMatch + 1) && tests[i].test(testVal.charAt(lastMatch + 1))) {
                                buffer[i] = testVal.charAt(++lastMatch);
                            }
                            if (i > lastMatch) break;
                        }
                    }
                    if (allow) writeBuffer();
                    else if (i < partialPosition) input.val('');
                    else input.val(input.val().substring(0, i + 1));
                    return partialPosition ? i : firstNonMaskPos;
                }

                input.on('input.mask', function() {
                    checkVal(false);
                }).data($.mask.dataName, function() {
                    return $.map(buffer, function(c, i) {
                        return tests[i] && c !== settings.placeholder.charAt(i) ? c : null;
                    }).join('');
                });

                checkVal(false);
            });
        }
    });
});