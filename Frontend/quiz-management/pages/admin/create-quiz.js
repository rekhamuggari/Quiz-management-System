import { useState,useEffect } from "react";
import { useRouter } from "next/router";
import Navbar from "../../components/Navbar";
import api from "../../services/api";

export default function CreateQuiz() {
  const router = useRouter();

  useEffect(() => {
  const token = localStorage.getItem("adminToken");
  if (!token) {
    router.push("/admin/login");
  }
}, []);

  const [title, setTitle] = useState("");
  const [questions, setQuestions] = useState([
    { questionText: "", options: "", correctAnswer: "" },
  ]);

  const addQuestion = () => {
    setQuestions([
      ...questions,
      { questionText: "", options: "", correctAnswer: "" },
    ]);
  };

  const updateQuestion = (index, field, value) => {
    const updated = [...questions];
    updated[index][field] = value;
    setQuestions(updated);
  };

  const submitQuiz = async () => {
    const payload = {
      title,
      questions: questions.map((q) => ({
        questionText: q.questionText,
        options: q.options ? JSON.stringify(q.options.split(",")) : null,
        correctAnswer: q.correctAnswer,
      })),
    };

    try {
      await api.post("/admin/quiz", payload);
      alert("Quiz created successfully");
      router.push("/");
    } catch (err) {
      alert("Failed to create quiz");
    }
  };

  return (
    <>
      <Navbar title="Create Quiz" />

      <div className="container mt-4">
        <div className="card p-4">
          <h4 className="mb-3">Quiz Details</h4>

          <input
            className="form-control mb-4"
            placeholder="Quiz Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />

          {questions.map((q, index) => (
            <div key={index} className="border p-3 mb-3 rounded">
              <h6>Question {index + 1}</h6>

              <input
                className="form-control mb-2"
                placeholder="Question text"
                value={q.questionText}
                onChange={(e) =>
                  updateQuestion(index, "questionText", e.target.value)
                }
              />

              <input
                className="form-control mb-2"
                placeholder="Options (comma separated)"
                value={q.options}
                onChange={(e) =>
                  updateQuestion(index, "options", e.target.value)
                }
              />

              <input
                className="form-control"
                placeholder="Correct Answer"
                value={q.correctAnswer}
                onChange={(e) =>
                  updateQuestion(index, "correctAnswer", e.target.value)
                }
              />
            </div>
          ))}

          <button
            className="btn btn-secondary mb-3"
            onClick={addQuestion}
          >
            Add Question
          </button>

          <button
            className="btn btn-primary w-100"
            onClick={submitQuiz}
          >
            Create Quiz
          </button>
        </div>
      </div>
    </>
  );
}
