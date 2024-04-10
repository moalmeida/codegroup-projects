'use strict';

const {
    useState,
    useCallback,
    useEffect,
    useMemo
} = React
const { createRoot } = ReactDOM


const useProjetoForm = (projeto) => {
    const [id] = useState(projeto?.id)
    const [nome, setNome] = useState(projeto?.nome)
    const [dataInicio, setDataInicio] = useState(projeto?.dataInicio)
    const [dataPrevisaoFim, setDataPrevistaFim] = useState(projeto?.dataPrevisaoFim)
    const [dataFim, setDataFim] = useState(projeto?.dataFim)
    const [descricao, setDescricao] = useState(projeto?.descricao)
    const [status, setStatus] = useState(projeto?.status)
    const [orcamento, setOrcamento] = useState(projeto?.orcamento)
    const [risco, setRisco] = useState(projeto?.risco)
    const [gerente, setGerente] = useState(projeto?.gerente?.id)

    const [listaStatus, setListaStatus] = useState([])
    const [listaGerente, setListaGerente] = useState([])

    const buscarStatus = useCallback(
        async () => {
            fetch('/api/projetos/status', {
                method: "GET"
            }).then((res) => res.json())
                .then((json) => {
                    setListaStatus(json)
                })
                .catch((err) => {
                    setListaStatus([])
                })
        },
        [setListaStatus],
    )

    const buscarGerentes = useCallback(
        async () => {
            fetch('/api/pessoas/gerentes', {
                method: "GET"
            }).then((res) => res.json())
                .then((json) => {
                    setListaGerente(json)
                })
                .catch((err) => {
                    setListaGerente([])
                })
        },
        [setListaGerente],
    )

    useEffect(() => {
        buscarStatus()
        buscarGerentes()
    }, [buscarStatus, buscarGerentes])

    return useMemo(
        () => ({
            id,
            nome,
            dataInicio,
            dataPrevisaoFim,
            dataFim,
            descricao,
            status,
            orcamento,
            risco,
            gerente,
            listaStatus,
            listaGerente,
            setNome,
            setDataInicio,
            setDataPrevistaFim,
            setDataFim,
            setDescricao,
            setStatus,
            setOrcamento,
            setRisco,
            setGerente,
        }),
        [
            id,
            nome,
            dataInicio,
            dataPrevisaoFim,
            dataFim,
            descricao,
            status,
            orcamento,
            risco,
            gerente,
            listaStatus,
            listaGerente,
            setNome,
            setDataInicio,
            setDataPrevistaFim,
            setDataFim,
            setDescricao,
            setStatus,
            setOrcamento,
            setRisco,
            setGerente,
        ],
    )
}

const useProjetos = () => {
    const [projetos, setProjetos] = useState([])
    const [projeto, setProjeto] = useState(null)
    const [error, setError] = useState(null)

    const buscarProjetos = useCallback(
        async () => {
            fetch('/api/projetos', {
                method: "GET"
            }).then((res) => res.json())
                .then((json) => {
                    setProjetos(json)
                })
                .catch((err) => {
                    setProjetos([])
                })
        },
        [setProjetos],
    )

    const buscarProjeto = useCallback(
        async ({ id }) => {
            fetch(`/api/projetos/${id}`, {
                method: "GET"
            }).then((res) => res.json())
                .then((json) => {
                    setProjeto(json)
                })
                .catch((err) => {
                    setProjeto(null)
                })
        },
        [setProjeto],
    )

    const criarProjeto = useCallback(
        async ({ projeto }) => {
            fetch(`/api/projetos`, {
                method: "POST",
                body: JSON.stringify(projeto),
            }).then((res) => res.json())
                .then((json) => {
                    setProjeto(json)
                })
                .catch((err) => {
                    setProjeto(null)
                })
        },
        [setProjeto],
    )

    const atualizarProjeto = useCallback(
        async ({ id, projeto }) => {
            fetch(`/api/projetos/${id}`, {
                method: "PATCH",
                body: JSON.stringify(projeto),
            }).then((res) => res.json())
                .then((json) => {
                    setProjeto(json)
                })
                .catch((err) => {
                    setProjeto(null)
                })
        },
        [setProjeto],
    )

    const removerProjeto = useCallback(
        async () => {
            setError(null)
            fetch(`/api/projetos/${id}`, {
                method: "DELETE"
            }).catch((err) => {
                setError(err)
            })
        },
        [setError],
    )

    return useMemo(
        () => ({
            projetos,
            projeto,
            error,
            buscarProjetos,
            buscarProjeto,
            criarProjeto,
            atualizarProjeto,
            removerProjeto,
        }),
        [
            projetos,
            projeto,
            error,
            buscarProjetos,
            buscarProjeto,
            criarProjeto,
            atualizarProjeto,
            removerProjeto,
        ],
    )
}

const ProjetoCard = ({ projeto, handleEditar, handleDeletar }) => {
    return (
        <div className="col h-100">
            <div className="card card-hover">
                <div className="card-body">
                    <div className="d-flex justify-content-between align-items-center pt-1">
                        <span className="badge bg-primary fs-5">{projeto.status}</span>
                        <span className="badge bg-primary fs-5">{projeto.risco}</span>
                    </div>
                    <h4 className="pt-4 pb-2 text-truncate-line-2">
                        {projeto.nome}
                    </h4>
                    <h5 className="pb-2 text-truncate-line-2">
                        {projeto.descricao}
                    </h5>
                    <div className="pb-2 fs-5 d-flex justify-content-between">
                        <div className=" d-flex align-items-center">
                            <i className="bi bi-cash-coin" style={{ fontSize: "1.75rem", color: "green" }}></i>
                            <span className="p-2">orçamento</span>
                        </div>
                        <div>
                            {projeto.orcamento}
                        </div>
                    </div>
                    <div className="pb-2 fs-5 d-flex justify-content-between">
                        <div className=" d-flex align-items-center">
                            <i className="bi bi-calendar" style={{ fontSize: "1.75rem", color: "black" }}></i>
                            <span className="p-2">data de início</span>
                        </div>
                        <div>
                            {projeto.dataInicio}
                        </div>
                    </div>
                    <div className="pb-2 fs-5 d-flex justify-content-between">
                        <div className=" d-flex align-items-center">
                            <i className="bi bi-calendar" style={{ fontSize: "1.75rem", color: "black" }}></i>
                            <span className="p-2">previsão de término</span>
                        </div>
                        <div>
                            {projeto.dataPrevisaoFim}
                        </div>
                    </div>
                    {projeto.dataFim && (
                        <div className="pb-2 fs-5 d-flex justify-content-between">
                            <div className=" d-flex align-items-center">
                                <i className="bi bi-calendar" style={{ fontSize: "1.75rem", color: "black" }}></i>
                                <span className="p-2">data de término</span>
                            </div>
                            <div>
                                {projeto.dataFim}
                            </div>
                        </div>
                    )}
                </div>
                <div className="card-footer">
                    <div className="row align-items-center g-0">
                        <div className="col">
                            <i className="bi bi-person-badge" style={{ fontSize: "1.75rem", color: "blue" }}></i>  {projeto.gerente.nome}
                        </div>
                        <div className="col-auto d-flex justify-content-between align-items-center">
                            <span className="p-2" onClick={() => { handleEditar(projeto) }}>
                                <i className="bi bi-pen-fill" style={{ fontSize: "1.75rem", color: "green" }} data-bs-toggle="modal" data-bs-target="#projetoFormModal"></i>
                            </span>
                            {projeto.permitidoRemover && (
                                <span className="p-2" onClick={() => { handleDeletar(projeto) }}>
                                    <i className="bi bi-trash" style={{ fontSize: "1.75rem", color: "red" }} data-bs-toggle="modal" data-bs-target="#projetoConfirmModal"></i>
                                </span>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

function ProjetoForm({ projeto }) {
    const {
        nome,
        dataInicio,
        gerente,
        dataPrevisaoFim,
        dataFim,
        orcamento,
        descricao,
        // risco,
        status,
        listaStatus,
        listaGerente,
        setNome,
        setDataInicio,
        setDataPrevistaFim,
        setDataFim,
        setDescricao,
        setStatus,
        setOrcamento,
        // setRisco,
        setGerente,
    } = useProjetoForm(projeto)

    const formatarData = (event) => {
        let input = event.target.value.replaceAll(/\D/g, '');
        if (input.length > 2) {
            input = `${input.slice(0, 2)}/${input.slice(2)}`;
        }
        if (input.length > 5) {
            input = `${input.slice(0, 5)}/${input.slice(5, 9)}`;
        }
        return input.slice(0, 10);
    }

    const handleDataInicio = (event) => setDataInicio(formatarData(event));
    const handleDataPrevistaFim = (event) => setDataPrevistaFim(formatarData(event));
    const handleDataFim = (event) => setDataFim(formatarData(event));

    return (
        <form>
            <div className="row">
                <fieldset>
                    <label className="form-label mt-4 fs-4" htmlFor="textarea_nome">nome</label>
                    <textarea className="form-control fs-4" id="textarea_nome" rows="2" onChange={(e) => setNome(e.target.value)}></textarea>
                </fieldset>
            </div>
            <div className="row">
                <div className="col">
                    <fieldset>
                        <label className="form-label mt-4 fs-4" htmlFor="input_data_inicio">data de inicio</label>
                        <input className="form-control fs-4" id="input_data_inicio" type="text" value={dataInicio} onChange={handleDataInicio} />
                    </fieldset>
                </div>
                <div className="col">
                    <fieldset>
                        <label className="form-label mt-4 fs-4" htmlFor="select_gerente">gerente responsável</label>
                        <select className="form-select fs-4" id="select_gerente" value={gerente} onChange={(e) => setGerente(e.target.value)}>
                            <option></option>
                            {listaGerente?.map(ls => {
                                return (
                                    <option value={ls.id}>{ls.nome}</option>
                                )
                            })}
                        </select>
                    </fieldset>
                </div>
            </div>
            <div className="row">
                <div className="col">
                    <fieldset>
                        <label className="form-label mt-4 fs-4" htmlFor="input_data_previsao_fim">previsão de término</label>
                        <input className="form-control fs-4" id="input_data_previsao_fim" type="text" value={dataPrevisaoFim} onChange={handleDataPrevistaFim} />
                    </fieldset>
                </div>
                <div className="col">
                    <fieldset>
                        <label className="form-label mt-4 fs-4" htmlFor="input_data_fim">data real de término</label>
                        <input className="form-control fs-4" id="input_data_fim" type="text" value={dataFim} onChange={handleDataFim} />
                    </fieldset>
                </div>
            </div>
            <div className="row">
                <div className="col">
                    <fieldset>
                        <label className="form-label mt-4 fs-4" htmlFor="input_orcamento">orçamento total</label>
                        <input className="form-control fs-4" id="input_orcamento" type="text" value={orcamento} onChange={(e) => setOrcamento(e.target.value)} />
                    </fieldset>
                </div>
                <div className="col">
                    <fieldset>
                        <label className="form-label mt-4 fs-4" htmlFor="select_status">Status</label>
                        <select className="form-select fs-4" id="select_status" value={status} onChange={(e) => setStatus(e.target.value)}>
                            <option></option>
                            {listaStatus?.map(ls => {
                                return (
                                    <option value={ls}>{ls}</option>
                                )
                            })}
                        </select>
                    </fieldset>
                </div>
            </div>
            <div className="row">
                <fieldset>
                    <label className="form-label mt-4 fs-4" htmlFor="textarea_descricao">Descrição</label>
                    <textarea className="form-control fs-4" id="textarea_descricao" rows="4" value={descricao} onChange={(e) => setDescricao(e.target.value)}></textarea>
                </fieldset>
            </div>
        </form>
    )

}

function ProjetoPage() {
    const { projetos, projeto, buscarProjetos } = useProjetos()

    useEffect(() => {
        buscarProjetos()
    }, [buscarProjetos])

    const handleNovo = () => {
        console.log('handleNovo')
    }

    const handleEditar = (projeto) => {
        console.log('handleEditar', projeto.id)
    }

    const handleSalvar = (projeto) => {
        console.log('handleSalvar')
    }


    const handleRemover = (projeto) => {
        console.log('handleDeletar', projeto.id)
    }

    return (
        <>
            <nav className="navbar navbar-dark bg-primary">
                <div className="container-fluid">
                    <span className="navbar-brand mb-0 h1">cadastro</span>
                </div>
            </nav>
            <div className="container mt-5">
                <div className="row">
                    <div>
                        <button type="button" className="btn btn-primary mb-2" data-bs-toggle="modal" data-bs-target="#projetoFormModal">
                            Adicionar Projeto
                        </button>
                    </div>

                </div>
                <div className="row row-cols-1 row-cols-md-2 row-cols-xl-3 g-4 pt-5">
                    {projetos?.map(projeto => {
                        return (
                            <ProjetoCard key={`projetocard_${projeto.id}`} projeto={projeto} handleEditar={handleEditar} handleDeletar={handleDeletar} />
                        )
                    })}
                </div>
            </div>
            <div className='modal modal-lg' id="projetoFormModal" tabIndex="-1" aria-labelledby="projetoFormModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="projetoFormModalLabel">Cadastrar Projeto</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                        </div>
                        <div className="modal-body">
                            <ProjetoForm projeto={projeto} />
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" aria-label="close">Cancelar</button>
                            <button type="button" className="btn btn-primary" onClick={handleSalvar}>Salvar</button>
                        </div>
                    </div>
                </div>
            </div>
            <div className='modal modal-lg' id="projetoConfirmModal" tabIndex="-1" aria-labelledby="projetoConfirmModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="projetoConfirmModalLabel">Remover Projeto</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                        </div>
                        <div className="modal-body">
                            Tem certeza que gostaria de remover esse projeto?
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal" aria-label="close">Cancelar</button>
                            <button type="button" className="btn btn-danger" onClick={handleRemover}>Remover</button>
                        </div>
                    </div>
                </div>
            </div>
            <footer className="bg-light text-left text-lg-start mt-5">
                <div className="text-left p-3">
                    &copy; 2024 CodeGroup
                </div>
            </footer>
        </>
    );
};


createRoot(document.getElementById("root")).render(<ProjetoPage />);