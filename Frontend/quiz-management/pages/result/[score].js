import { useRouter } from "next/router";
import Navbar from "../../components/Navbar";

export default function Result() {
  const router = useRouter();
  const { score } = router.query;

  return (
    <>
      <Navbar title="Quiz Result" />

      <div className="container text-center mt-5">
        <h2>Your Score</h2>
        <h1 className="text-success">{score}</h1>

        <button
          className="btn btn-primary mt-4"
          onClick={() => router.push("/")}
        >
          Go Home
        </button>
      </div>
    </>
  );
}
