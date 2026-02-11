import { useState, useEffect } from 'react'
import axios from 'axios'
import './App.css'

const api = axios.create({ baseURL: 'http://localhost:8081/api' });

function App() {
  const [hero, setHero] = useState(null)
  const [tasks, setTasks] = useState([])
  const [loading, setLoading] = useState(true)

  const loadDashboard = async () => {
    try {
      // 1. Busca os dados do Herói (Ajuste o endpoint se necessário para retornar o objeto User completo)
      const resHero = await api.post('/auth/login', { username: 'HEROO27', password: '123' });

      // 2. Busca as Tasks do HEROO27 (ID 1)
      const resTasks = await api.get('/tasks/hero/1');

      setHero(resHero.data); // Se o seu login retornar o objeto User, aqui teremos level, xp, etc.
      setTasks(resTasks.data);
    } catch (err) {
      console.error("Erro na aventura:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { loadDashboard(); }, []);

  const completeTask = async (taskId) => {
    await api.patch(`/tasks/${taskId}/complete`);
    loadDashboard(); // Recarrega para ver o Level Up em tempo real!
  };

  if (loading) return <div className="loading">Carregando mapa...</div>

  return (
    <div className="App">
      <header className="hero-header">
        <h1>{hero?.username || 'HEROO27'}</h1>
        <div className="stats">
          <span>Nível {hero?.level || 2}</span>
          <div className="xp-bar-container">
            <div className="xp-bar-fill" style={{ width: `${(hero?.currentXp / hero?.nextLevelXp) * 100}%` }}></div>
          </div>
          <span>{hero?.currentXp || 0} / {hero?.nextLevelXp || 150} XP</span>
        </div>
      </header>

      <main className="task-list">
        <h2>Suas Missões Diárias</h2>
        {tasks.map(task => (
          <div key={task.id} className={`task-card ${task.completed ? 'done' : ''}`}>
            <h3>{task.title}</h3>
            <p>{task.xpReward} XP</p>
            {!task.completed && (
              <button onClick={() => completeTask(task.id)}>Finalizar Missão</button>
            )}
          </div>
        ))}
      </main>
    </div>
  )
}

export default App