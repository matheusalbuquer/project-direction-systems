document.addEventListener("DOMContentLoaded", async () => {
  const token = localStorage.getItem("token");

  if (!token) {
    window.location.href = "../index.html";
    return;
  }

  try {
    const response = await fetch("http://localhost:8080/produto", {
      headers: {
        Authorization: "Bearer " + token,
      },
    });

    if (!response.ok) {
      throw new Error("Erro ao buscar dados");
    }

    const produtos = await response.json();

    const lista = document.getElementById("listaProdutos");
    lista.innerHTML = "";

    produtos.forEach((produto) => {
      const card = document.createElement("div");
      card.style.border = "1px solid #ccc";
      card.style.padding = "10px";
      card.style.margin = "10px";
      card.style.borderRadius = "8px";

      card.innerHTML = `
        <h3>${produto.nome}</h3>
        <p>Categoria: ${produto.categoria}</p>
        <p>Preço: R$ ${produto.preco}</p>
        <p>Quantidade: ${produto.quantidade}</p>
      `;

      lista.appendChild(card);
    });
  } catch (error) {
    console.error(error);
    localStorage.removeItem("token");
    window.location.href = "../index.html";
  }
});
