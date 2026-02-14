const form = document.getElementById("loginForm");
const message = document.getElementById("message");

form.addEventListener("submit", async function (e) {
  e.preventDefault();

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  try {
    const response = await fetch("http://localhost:8080/usuario/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: email,
        senha: password,
      }),
    });

    if (!response.ok) {
      throw new Error("Erro no login");
    }

    const data = await response.text();

    // Salva o token
    localStorage.setItem("token", data.token);

    message.style.color = "green";
    message.textContent = "Login realizado com sucesso!";

    // Redireciona (opcional)
    setTimeout(() => {
      window.location.href = "dashboard.html";
    }, 1000);
  } catch (error) {
    message.style.color = "red";
    message.textContent = "Email ou senha inválidos!";
  }
});
