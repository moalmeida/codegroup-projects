// 'use strict';

const ProjectHome = () => {
    return (
        <>
            <div class="container mt-5">
                <button type="button" class="btn btn-success mb-2" data-bs-toggle="modal" data-bs-target="#addItemModal">Adicionar</button>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Item Name</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody id="itemTableBody">
                    </tbody>
                </table>
            </div>
            <div class="modal fade" id="addItemModal" tabindex="-1" aria-labelledby="addItemModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addItemModalLabel">Cadastrar</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input type="text" class="form-control" id="newItemName" placeholder="Item Name" />
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" onclick="cancelItem()">Salvar</button>
                            <button type="button" class="btn btn-primary" onclick="addItem()">Salvar</button>
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
        <nav class="navbar navbar-dark bg-primary">
            <div class="container-fluid">
                <span class="navbar-brand mb-0 h1">cadastro</span>
            </div>
        </nav>
    )
}


const Footer = () => {
    return (
        <footer class="bg-light text-center text-lg-start mt-5">
            <div class="text-left p-3">
                &copy; 2024 CodeGroup
            </div>
        </footer>
    )
}



function ProjectPage() {
    return (
        <>
            <Header />
            <ProjectHome />
            <Footer />
        </>
    );
};

// const router = ReactRouter.createBrowserRouter([
//     //     // //     {
//     //     // //         path: '/',
//     //     // //         element: <ReactRouterDOM.Navigate to={'/projetos'} replace />,
//     //     // //     },
//     {
//         path: '/projetos',
//         element: (
//             <React.Suspense fallback={<SplashScreen />}>
//                 {/* <ReactRouterDOM.Outlet /> */}
//             </React.Suspense>
//         ),
//         children: [
//             { element: <ProjectPage />, index: true },
//         ],
//     },
//     //     // //     // { path: '*', element: <ReactRouterDOM.Navigate to="/404" replace /> },
// ]);


ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <React.Suspense fallback={<SplashScreen />}>
            {/* <ReactRouterDOM.RouterProvider router={router} /> */}
            <ProjectPage />
        </React.Suspense>
    </React.StrictMode>
);
