import { useState } from "react";
import { useRouter } from "next/router";
import Navbar from "../../components/Navbar";
import api from "../../services/api";

export default function AdminLogin() {
  const router = useRouter();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);

  const login = async () => {
    if (!username || !password) {
      alert("Username and password required");
      return;
    }

    try {
      setLoading(true);

      const res = await api.post("/admin/login", {
        username,
        password,
      });

      // store token (simple approach for assignment)
      localStorage.setItem("adminToken", res.data.token);

      router.push("/admin/create-quiz");
    } catch (err) {
      alert(
        err?.response?.data?.message || "Invalid credentials"
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <Navbar title="Admin Login" />

      <div className="container mt-5" style={{ maxWidth: "400px" }}>
        <div className="card p-4 shadow">
          <h5 className="text-center mb-3">Admin Login</h5>

          <input
            className="form-control mb-3"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />

          <input
            type="password"
            className="form-control mb-3"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button
            className="btn btn-primary w-100"
            onClick={login}
            disabled={loading}
          >
            {loading ? "Logging in..." : "Login"}
          </button>
        </div>
      </div>
    </>
  );
}
