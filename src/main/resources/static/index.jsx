'use strict';

const { StrictMode, Suspense, useState, useCallback, useEffect, useMemo } = React
const { createRoot } = ReactDOM

const useProjetos = () => {
    const [projetos, setProjetos] = useState(null)
    const [projeto, setProjeto] = useState(null)
    const [loading, setLoading] = useState(false)
    const [error, setError] = useState(null)

    const buscarProjetos = useCallback(
        async () => {
            setLoading(true)
            setError(null)
            fetch('/api/projetos', {
                method: "GET"
            }).then((res) => res.json())
                .then((json) => {
                    setProjetos(json)
                })
                .catch((err) => {
                    setError(err)
                    setProjetos(null)
                })
                .finally(() => {
                    setLoading(false)
                })
        },
        [setLoading, setProjetos, setError],
    )

    const buscarProjeto = useCallback(
        async ({ id }) => {
            setLoading(true)
            setError(null)
            fetch(`/api/projetos/${id}`, {
                method: "GET"
            }).then((res) => res.json())
                .then((json) => {
                    setProjeto(json)
                })
                .catch((err) => {
                    setError(err)
                    setProjeto(null)
                })
                .finally(() => {
                    setLoading(false)
                })
        },
        [setLoading, setProjeto, setError],
    )

    const criarProjeto = useCallback(
        async ({ projeto }) => {
            setLoading(true)
            setError(null)
            fetch(`/api/projetos`, {
                method: "POST",
                body: JSON.stringify(projeto),
            }).then((res) => res.json())
                .then((json) => {
                    setProjeto(json)
                })
                .catch((err) => {
                    setError(err)
                    setProjeto(null)
                })
                .finally(() => {
                    setLoading(false)
                })
        },
        [setLoading, setProjeto, setError],
    )

    const atualizarProjeto = useCallback(
        async ({ id, projeto }) => {
            setLoading(true)
            setError(null)
            fetch(`/api/projetos/${id}`, {
                method: "PATCH",
                body: JSON.stringify(projeto),
            }).then((res) => res.json())
                .then((json) => {
                    setProjeto(json)
                })
                .catch((err) => {
                    setError(err)
                    setProjeto(null)
                })
                .finally(() => {
                    setLoading(false)
                })
        },
        [setLoading, setProjeto, setError],
    )

    const removerProjeto = useCallback(
        async () => {
            setLoading(true)
            setError(null)
            fetch(`/api/projetos/${id}`, {
                method: "DELETE"
            }).then((res) => res.json())
                .catch((err) => {
                    setError(err)
                })
                .finally(() => {
                    setLoading(false)
                })
        },
        [setLoading, setError],
    )

    return useMemo(
        () => ({
            projetos,
            projeto,
            loading,
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
            loading,
            error,
            buscarProjetos,
            buscarProjeto,
            criarProjeto,
            atualizarProjeto,
            removerProjeto,
        ],
    )
}


const ProjectHome = ({ projetos, salvarProjeto }) => {

    return (
        <>
            <div className="container mt-5">
                <button type="button" className="btn btn-success mb-2" data-bs-toggle="modal" data-bs-target="#addItemModal">Adicionar</button>
                <table className="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Item Name</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody id="itemTableBody">
                        {projetos?.map(projeto => {
                            return (
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            )
                        })}
                    </tbody>
                </table>
            </div>
            <div className="modal fade" id="addItemModal" tabIndex="-1" aria-labelledby="addItemModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="addItemModalLabel">Cadastrar</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="close"></button>
                        </div>
                        <div className="modal-body">
                            <input type="text" className="form-control" id="newItemName" placeholder="Item Name" />
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-primary" onClick={() => { console.log("cancelou") }} data-bs-dismiss="modal" aria-label="close">Cancelar</button>
                            <button type="button" className="btn btn-primary" onClick={salvarProjeto}>Salvar</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}


const SplashScreen = () => {
    return (
        <>carregando ...</>
    )
}


const Header = () => {
    return (
        <nav className="navbar navbar-dark bg-primary">
            <div className="container-fluid">
                <span className="navbar-brand mb-0 h1">cadastro</span>
            </div>
        </nav>
    )
}


const Footer = () => {
    return (
        <footer className="bg-light text-center text-lg-start mt-5">
            <div className="text-left p-3">
                &copy; 2024 CodeGroup
            </div>
        </footer>
    )
}

function ProjectPage() {
    const { projetos, loading, buscarProjetos } = useProjetos()

    useEffect(() => {
        console.log('buscarProjetos', Date())
        buscarProjetos()
    }, [buscarProjetos])

    const handleSalvarProjeto = () => {

    }

    return (
        <>
            <Header />
            {!loading && (<ProjectHome projetos={projetos} salvarProjeto={handleSalvarProjeto} />)}
            <Footer />
        </>
    );
};

createRoot(document.getElementById("root")).render(
    <Suspense fallback={<SplashScreen />}>
        <ProjectPage />
    </Suspense>
);
