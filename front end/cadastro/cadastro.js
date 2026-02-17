const form = document.getElementById("cadastroForm");
const message = document.getElementById("message");

form.addEventListener("submit", async function (e) {
  e.preventDefault();

  const nome = document.getElementById("nome").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  try {
    const response = await fetch("http://localhost:8080/usuario/cadastro", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nome: nome,
        email: email,
        senha: password,
      }),
    });

    if (!response.ok) {
      throw new Error("Erro no cadastro");
    }

    const data = await response.json();

    // 🔐 salva token
    localStorage.setItem("token", data.token);

    // ✅ vai direto pro dashboard
    window.location.href = "/index.html";
  } catch (error) {
    message.style.color = "red";
    message.textContent = "Erro no cadastro";
  }
});
