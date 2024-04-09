
import React from 'react';
import ReactDOM from 'react-dom';

// React Component
const App = () => {
    console.log("TESTE")
    return (
        <div>
            <h1>Hello, React!</h1>
        </div>
    );
};

ReactDOM.render(<App />, document.getElementById('root'));