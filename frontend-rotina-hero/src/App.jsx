import { useState, useEffect } from 'react'
import axios from 'axios'
import './App.css'

const api = axios.create({ baseURL: 'http://localhost:8081/api' });

function App() {
  // ESTADOS QUE ESTAVAM FALTANDO
  const [hero, setHero] = useState(null)
  const [tasks, setTasks] = useState([])
  const [loading, setLoading] = useState(true)
  const [levelUpEffect, setLevelUpEffect] = useState(false)
  const [newTask, setNewTask] = useState({
    title: '',
    xpReward: 0,
    userId: 1
  })

  const loadDashboard = async () => {
    try {
      const resHero = await api.post('/auth/login', { username: 'HEROO27', password: '123' });
      const resTasks = await api.get('/tasks/hero/1');

      const newHero = resHero.data;

      // Lógica de Level Up: Compara o nível anterior com o novo
      if (hero && newHero.level > hero.level) {
        setLevelUpEffect(true);
        setTimeout(() => setLevelUpEffect(false), 2000);
      }

      setHero(newHero);
      setTasks(resTasks.data);
    } catch (err) {
      console.error("Erro na aventura:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { loadDashboard(); }, []);

  // Função para criar a quest
  const handleCreateTask = async (e) => {
    e.preventDefault();
    try {
      await api.post('/tasks', newTask);
      setNewTask({ title: '', description: '', xpReward: 0, userId: 1 }); // Limpa o form
      loadDashboard(); // Recarrega a lista e o XP
    } catch (err) {
      console.error("Erro ao criar quest:", err);
    }
  };

  const completeTask = async (taskId) => {
    await api.patch(`/tasks/${taskId}/complete`);
    loadDashboard();
  };

  if (loading) return <div className="loading">Carregando mapa...</div>

  return (
    <div className="App">
      <header className="hero-header">
        <h1>{hero?.username || 'HEROO27'}</h1>
        <div className="stats">
          <span>Nível {hero?.level}</span>
          <div className="xp-bar-container">
            <div className={`xp-bar-fill ${levelUpEffect ? 'level-up-anim' : ''}`}
                 style={{ width: `${(hero?.currentXp / hero?.nextLevelXp) * 100}%` }}>
            </div>
          </div>
          <span>{hero?.currentXp} / {hero?.nextLevelXp} XP</span>
        </div>
      </header>

      {/* Novo Formulário de Quests */}
      <section className="quest-form">
        <h2>Nova Quest</h2>
        <form onSubmit={handleCreateTask}>
          <input
            type="text"
            placeholder="Título da Missão"
            value={newTask.title}
            onChange={(e) => setNewTask({...newTask, title: e.target.value})}
            required
          />
          <input
            type="number"
            placeholder="Recompensa de XP"
            value={newTask.xpReward}
            onChange={(e) => setNewTask({...newTask, xpReward: parseInt(e.target.value)})}
            required
          />
          <button type="submit">Adicionar ao Mural</button>
        </form>
      </section>

      <main className="task-list">
        <h2>Mural de Missões</h2>
        {tasks.map(task => (
          <div key={task.id} className={`task-card ${task.completed ? 'done' : ''}`}>
            <div>
              <h3>{task.title}</h3>
              <p>{task.xpReward} XP</p>
            </div>
            {!task.completed && (
              <button onClick={() => completeTask(task.id)}>Finalizar</button>
            )}
          </div>
        ))}
      </main>
    </div>
  )
}

export default App