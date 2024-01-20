import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import PageLayout from 'components/layout/PageLayout';
import 'styles/index.scss'
import Login from 'pages/auth/Login';
import Register from 'pages/auth/Register';
import Profile from 'pages/profile/Profile';
import Fallback from 'pages/Fallback';
import Logout from 'pages/auth/Logout';
import AddictionsList from 'pages/addictions/AddictionsList';
import AddictionDetails from 'pages/addictions/AddictionDetails';

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
        element: <Fallback />,
      },
      {
        path: "/profile",
        element: <Profile />
      },
      {
        path: "/addictions",
        element: <AddictionsList />
      },
      {
        path: "/addiction/:addictionId",
        element: <AddictionDetails />
      }
    ]
  },
  //For paths without layout
  {
    path: "/",
    children: [
      {
        path: "/login",
        element: <Login />
      },
      {
        path: "/register",
        element: <Register />
      },
      {
        path: "/logout",
        element: <Logout />
      }
    ]
  }

]);

root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);