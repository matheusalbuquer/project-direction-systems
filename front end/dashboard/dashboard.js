let produtoEmEdicao = null;

document.addEventListener("DOMContentLoaded", async () => {
  const token = localStorage.getItem("token");

  if (!token) {
    window.location.href = "../index.html";
    return;
  }

  try {
    await carregarProdutos();
    await carregarUsuario();
  } catch (error) {
    console.error(error);
    localStorage.removeItem("token");
    window.location.href = "../index.html";
  }
});

/*mostra os prodtuo */

async function carregarProdutos() {
  const token = localStorage.getItem("token");

  const response = await fetch("http://localhost:8080/produto", {
    headers: {
      Authorization: "Bearer " + token,
    },
  });

  if (!response.ok) {
    throw new Error("Erro ao buscar produtos");
  }

  const produtos = await response.json();

  const lista = document.getElementById("listaProdutos");
  lista.innerHTML = "";

  produtos.forEach((produto) => {
    const card = document.createElement("div");
    card.classList.add("card-produto");

    card.innerHTML = `
      <h3>${produto.nome}</h3>
      <p><strong>Categoria:</strong> ${produto.categoria}</p>
      <p><strong>Preço:</strong> R$ ${Number(produto.preco).toFixed(2)}</p>
      <p><strong>Quantidade:</strong> ${produto.quantidade}</p>

      <button onclick="abrirModalEditar(${produto.id}, '${produto.nome}', '${produto.categoria}', ${produto.preco}, ${produto.quantidade})" class="btn-editar">
        Editar
      </button>

      <button onclick="deletarProduto(${produto.id})" class="btn-apagar">
        Apagar
      </button>
    `;

    lista.appendChild(card);
  });
}

//*mostra os usuario//

async function carregarUsuario() {
  const token = localStorage.getItem("token");

  const response = await fetch("http://localhost:8080/usuario/nome", {
    headers: {
      Authorization: "Bearer " + token,
    },
  });

  if (!response.ok) {
    throw new Error("Erro ao buscar usuario");
  }

  const dataUsuario = await response.json();
  document.getElementById("usuarioNome").innerText = dataUsuario.nome;
}

//**abri para criar */ */

function abrirModal() {
  produtoEmEdicao = null;

  document.getElementById("nomeProduto").value = "";
  document.getElementById("categoriaProduto").value = "";
  document.getElementById("precoProduto").value = "";
  document.getElementById("quantidadeProduto").value = "";

  document.getElementById("modalProduto").style.display = "flex";
}

/*abri o modal*/

function abrirModalEditar(id, nome, categoria, preco, quantidade) {
  produtoEmEdicao = id;

  document.getElementById("nomeProduto").value = nome;
  document.getElementById("categoriaProduto").value = categoria;
  document.getElementById("precoProduto").value = preco;
  document.getElementById("quantidadeProduto").value = quantidade;

  document.getElementById("modalProduto").style.display = "flex";
}

/*salvar*/

async function salvarProduto() {
  const token = localStorage.getItem("token");

  const nome = document.getElementById("nomeProduto").value;
  const categoria = document.getElementById("categoriaProduto").value;
  const preco = document.getElementById("precoProduto").value;
  const quantidade = document.getElementById("quantidadeProduto").value;

  const metodo = produtoEmEdicao ? "PUT" : "POST";
  const url = produtoEmEdicao
    ? `http://localhost:8080/produto/${produtoEmEdicao}`
    : "http://localhost:8080/produto";

  try {
    const response = await fetch(url, {
      method: metodo,
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
      body: JSON.stringify({
        nome,
        categoria,
        preco,
        quantidade,
      }),
    });

    if (!response.ok) {
      throw new Error("Erro ao salvar produto");
    }

    fecharModal();
    await carregarProdutos();
  } catch (error) {
    alert("Erro ao salvar produto");
    console.error(error);
  }
}

/*apagar*/
async function deletarProduto(id) {
  const token = localStorage.getItem("token");

  const confirmar = confirm("Tem certeza que deseja apagar este produto?");
  if (!confirmar) return;

  try {
    const response = await fetch(`http://localhost:8080/produto/${id}`, {
      method: "DELETE",
      headers: {
        Authorization: "Bearer " + token,
      },
    });

    if (!response.ok) {
      throw new Error("Erro ao deletar produto");
    }

    await carregarProdutos();
  } catch (error) {
    alert("Erro ao deletar produto");
    console.error(error);
  }
}

function fecharModal() {
  document.getElementById("modalProduto").style.display = "none";
}

function logout() {
  localStorage.removeItem("token");
  window.location.href = "../index.html";
}
