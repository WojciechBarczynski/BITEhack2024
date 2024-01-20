import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Home from 'pages/Home';
import PageLayout from 'components/layout/PageLayout';
import 'styles/index.scss'

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);

const router = createBrowserRouter([
  //For paths with default (Page) layout
  {
    path: "/",
    element: <PageLayout />,
    children: [
      {
        path: "/",
        element: <Home />,
      },
      {
        path: "/test",
        element: <div>test</div>
      }
    ]
  },
  //For paths without layout
  {
    path: "/",
    children: [
      {
        path: "/test2",
        element: <div>test2</div>
      }
    ]
  }

]);

root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);