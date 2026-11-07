
const API_URL = "http://localhost:8080/aluno";

const mensagem = document.getElementById("mensagem");

// MOSTRAR MENSAGEM
function mostrarMensagem(texto, tipo = "sucesso") {
    mensagem.textContent = texto;
    mensagem.className = `mensagem ${tipo}`;
}

// VALIDAR NOTA
function validarNota(valor) {
    const nota = Number(valor);

    if (!Number.isFinite(nota) || nota < 0 || nota > 10) {
        throw new Error("A nota deve estar entre 0 e 10.");
    }

    return nota;
}

// TRATAR RESPOSTA DA API
async function tratarResposta(response) {
    if (!response.ok) {
        const texto = await response.text();
        throw new Error(texto || `Erro HTTP ${response.status}`);
    }

    const tipo = response.headers.get("content-type") || "";

    if (tipo.includes("application/json")) {
        return response.json();
    }

    return null;
}

// CADASTRAR ALUNO
document.getElementById("aluno-form").addEventListener("submit", async (event) => {
    event.preventDefault();

    try {
        const aluno = {
            nome: document.getElementById("nome").value.trim(),
            email: document.getElementById("email").value.trim(),
            nota: validarNota(document.getElementById("nota").value)
        };

        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(aluno)
        });

        await tratarResposta(response);

        mostrarMensagem("Aluno cadastrado com sucesso!");
        event.target.reset();

    } catch (error) {
        mostrarMensagem(`Erro ao cadastrar: ${error.message}`, "erro");
    }
});

// BUSCAR ALUNO PELO EMAIL
document.getElementById("busca-form").addEventListener("submit", async (event) => {
    event.preventDefault();

    const email = document.getElementById("email-busca").value.trim();
    const resultado = document.getElementById("resultado-busca");

    try {
        const response = await fetch(
            `${API_URL}?email=${encodeURIComponent(email)}`
        );

        const aluno = await tratarResposta(response);

        if (!aluno) {
            throw new Error("Aluno não encontrado.");
        }

        resultado.replaceChildren();

        const dados = document.createElement("div");

        const id = document.createElement("p");
        id.textContent = `ID: ${aluno.id}`;

        const nome = document.createElement("p");
        nome.textContent = `Nome: ${aluno.nome}`;

        const emailAluno = document.createElement("p");
        emailAluno.textContent = `E-mail: ${aluno.email}`;

        const nota = document.createElement("p");
        nota.textContent = `Nota escolar: ${aluno.nota ?? "Não informada"}`;

        dados.append(id, nome, emailAluno, nota);
        resultado.appendChild(dados);

        mostrarMensagem("Aluno encontrado!");

    } catch (error) {
        resultado.replaceChildren();
        mostrarMensagem(`Erro ao buscar: ${error.message}`, "erro");
    }
});

// ATUALIZAR ALUNO
document.getElementById("atualizar-form").addEventListener("submit", async (event) => {
    event.preventDefault();

    const emailOriginal = document.getElementById("email-original").value.trim();
    const novoNome = document.getElementById("novo-nome").value.trim();
    const novoEmail = document.getElementById("novo-email").value.trim();
    const novaNota = document.getElementById("nova-nota").value;

    if (!novoNome && !novoEmail && novaNota === "") {
        mostrarMensagem("Informe pelo menos um dado para atualizar.", "erro");
        return;
    }

    const aluno = {};

    if (novoNome) {
        aluno.nome = novoNome;
    }

    if (novoEmail) {
        aluno.email = novoEmail;
    }

    try {
        if (novaNota !== "") {
            aluno.nota = validarNota(novaNota);
        }

        const response = await fetch(
            `${API_URL}?email=${encodeURIComponent(emailOriginal)}`,
            {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(aluno)
            }
        );

        await tratarResposta(response);

        mostrarMensagem("Aluno atualizado com sucesso!");
        event.target.reset();

    } catch (error) {
        mostrarMensagem(`Erro ao atualizar: ${error.message}`, "erro");
    }
});

// EXCLUIR ALUNO PELO EMAIL
document.getElementById("excluir-form").addEventListener("submit", async (event) => {
    event.preventDefault();

    const email = document.getElementById("email-excluir").value.trim();

    const confirmar = confirm(
        "Tem certeza que deseja excluir este aluno?"
    );

    if (!confirmar) {
        return;
    }

    try {
        const response = await fetch(
            `${API_URL}?email=${encodeURIComponent(email)}`,
            {
                method: "DELETE"
            }
        );

        await tratarResposta(response);

        mostrarMensagem("Aluno excluído com sucesso!");
        event.target.reset();

    } catch (error) {
        mostrarMensagem(`Erro ao excluir: ${error.message}`, "erro");
    }
});
