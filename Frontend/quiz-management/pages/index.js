import { useState } from "react";
import { useRouter } from "next/router";
import api from "../services/api";
import Navbar from "../components/Navbar";

export default function Home() {
  const router = useRouter();
  const [quizzes, setQuizzes] = useState([]);
  const [showQuizzes, setShowQuizzes] = useState(false);

  const handlePlayQuiz = async () => {
    try {
      const res = await api.get("/quiz");
      setQuizzes(res.data);
      setShowQuizzes(true);
    } catch {
      alert("Failed to fetch quizzes");
    }
  };

  return (
    <>
      <Navbar title="Quiz Management System" />

      <div className="container text-center mt-5">
        {!showQuizzes ? (
          <>
            <h1 className="mb-4">Welcome to Quiz Management</h1>

            <button
              className="btn btn-primary btn-lg me-3"
              onClick={() => router.push("/admin/login")}
            >
              Create Quiz
            </button>

            <button
              className="btn btn-success btn-lg"
              onClick={handlePlayQuiz}
            >
              Play Quiz
            </button>
          </>
        ) : (
          <>
            <h3 className="mb-4">Select a Quiz</h3>

            <div className="list-group">
              {quizzes.map((quiz) => (
                <button
                  key={quiz.id}
                  className="list-group-item list-group-item-action"
                  onClick={() => router.push(`/quiz/${quiz.id}`)}
                >
                  {quiz.title}
                </button>
              ))}
            </div>

            <button
              className="btn btn-secondary mt-4"
              onClick={() => setShowQuizzes(false)}
            >
              Back
            </button>
          </>
        )}
      </div>
    </>
  );
}
