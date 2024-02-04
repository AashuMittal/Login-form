import React, { useEffect, useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Link } from "react-router-dom";

const Hloo = () => {
  const [data, setData] = useState([]);
  const fetchApi = async () => {
    try {
      const res = await fetch("http://localhost:8080/sign_in");
      if (!res.ok) {
        throw new Error(`HTTP error! Status: ${res.status}`);
      }
      const result = await res.json();
      setData(result);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    fetchApi();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!password || !email) {
        toast.error("🦄 Invalid Data!", { /* ... */ });
        return;
    }

    const userdata = {
        password,
        email,
    };

    try {
        const response = await fetch("http://localhost:8080/sign_in", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(userdata),
        });

        const result = await response.json();

        if (response.ok) {
            // Login successful
            toast.success("🦄 Login Successful!", { /* ... */ });
        } else {
            // Login failed
            toast.error(`🦄 ${result.error}`, { /* ... */ });
        }

        setPassword("");
        setEmail("");
    } catch (error) {
        console.error("Error submitting form:", error);
    }
};

  return (
    <>
      <ToastContainer
        position="top-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"
      ></ToastContainer>
      <div className="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
        <div className="sm:mx-auto sm:w-full sm:max-w-sm">
          <img
            className="mx-auto h-10 w-auto"
            src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600"
            alt="Your Company"
          />
          <h2 className="mt-10 text-center text-2xl font-bold leading-7 tracking-tight text-gray-900">
            Login Form
          </h2>
        </div>

        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          <div className="space-y-6">
            <div>
              {/* <label
                htmlFor="name"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Name
              </label> */}
              {/* <div className="mt-2">
                <input
                  id="name"
                  name="name"
                  type="text"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  autoComplete="text"
                  required
                  className="block w-full border-2 rounded-md py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                />
              </div> */}
            </div>

            <div>
              <div className="flex items-center justify-between">
                <label
                  htmlFor="roll"
                  className="block text-sm font-medium leading-6 text-gray-900"
                >
                  Password
                </label>
              </div>
              <div className="mt-2">
                <input
                  id="roll"
                  name="roll"
                  type="password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  autoComplete="current-password"
                  required
                  className="block w-full rounded-md border-2 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <div className="flex items-center justify-between">
                <label
                  htmlFor="branch"
                  className="block text-sm font-medium leading-6 text-gray-900"
                >
                  Email:
                </label>
              </div>
              <div className="mt-2">
                <input
                  id="branch"
                  name="branch"
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  autoComplete="current-password"
                  required
                  className="block w-full rounded-md border-2 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <button
                onClick={handleSubmit}
                className="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      </div>
      <div className="py-10 items-center justify-center mx-auto w-full flex flex-col">
        {data.map((e, index) => (
          <div key={index} className="flex space-x-12">
            <p className="font-semibold text-purple-500">{e.name}</p>
            <p className="font-semibold text-red-500">{e.password}</p>
            <p className="font-semibold text-emerald-500">{e.dob}</p>
          </div>
        ))}
        <Link to="/sign_up">Signup Page</Link>
      </div>
    </>
  );
};

export default Hloo;
