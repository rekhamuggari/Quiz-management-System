import { useEffect, useState } from "react";
import { useRouter } from "next/router";
import api from "../../services/api";
import Navbar from "../../components/Navbar";

export default function QuizPage() {
  const router = useRouter();
  const { id } = router.query;

  const [quiz, setQuiz] = useState(null);
  const [answers, setAnswers] = useState({});

  useEffect(() => {
    if (id) {
      api.get(`/quiz/${id}`).then((res) => setQuiz(res.data));
    }
  }, [id]);

  const handleChange = (qid, value) => {
    setAnswers({ ...answers, [qid]: value });
  };

  const handleSubmit = async () => {
    const score = await api
      .post(`/quiz/${id}/submit`, { answers })
      .then((res) => res.data);

    router.push(`/result/${score}`);
  };

  if (!quiz) return <p className="text-center mt-5">Loading...</p>;

  return (
    <>
      <Navbar title="Take Quiz" />

      <div className="container mt-4">
        <h3 className="mb-4">{quiz.title}</h3>

        {quiz.questions.map((q, index) => (
          <div key={q.id} className="card mb-3">
            <div className="card-body">
              <h6>
                Q{index + 1}. {q.questionText}
              </h6>

              {q.options ? (
                JSON.parse(q.options).map((opt, i) => (
                  <div className="form-check" key={i}>
                    <input
                      className="form-check-input"
                      type="radio"
                      name={`q-${q.id}`}
                      value={opt}
                      onChange={(e) =>
                        handleChange(q.id, e.target.value)
                      }
                    />
                    <label className="form-check-label">{opt}</label>
                  </div>
                ))
              ) : (
                <input
                  className="form-control mt-2"
                  placeholder="Your answer"
                  onChange={(e) =>
                    handleChange(q.id, e.target.value)
                  }
                />
              )}
            </div>
          </div>
        ))}

        <button className="btn btn-primary" onClick={handleSubmit}>
          Submit Quiz
        </button>
      </div>
    </>
  );
}
