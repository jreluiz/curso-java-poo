# 🛠️ Preparação do Ambiente

Faça esta configuração **antes da Aula 01** (ou na própria aula, com calma).

## 1. O JDK

Java precisa de duas coisas: um **compilador** (transforma seu `.java` em bytecode) e uma **máquina virtual** (executa o bytecode). As duas vêm no **JDK** — *Java Development Kit*.

1. Baixe o **JDK 21 (LTS)** em <https://adoptium.net/temurin/releases/> (Eclipse Temurin, gratuito) ou em <https://www.oracle.com/br/java/technologies/downloads/>;
2. Instale aceitando as opções padrão — no Windows, **marque a opção que adiciona o Java ao `PATH`**;
3. **Feche e reabra o terminal** (o `PATH` só é lido na abertura) e verifique:

```bash
java -version      # deve mostrar algo como openjdk version "21.0.x"
javac -version     # deve mostrar javac 21.0.x
```

> ⚠️ Se `java` responde mas `javac` diz *comando não encontrado*, você instalou só o **JRE** (executor), não o **JDK** (kit de desenvolvimento). Instale o JDK.

Teste rápido: crie um arquivo `Teste.java` com o conteúdo abaixo e rode `java Teste.java`.

```java
public class Teste {
    public static void main(String[] args) {
        System.out.println("funciona!");
    }
}
```

> 💡 Desde o **JDK 11**, `java Arquivo.java` compila e executa num passo só — ótimo para exercícios de um arquivo. Na Aula 01 você vai fazer o caminho longo (`javac` + `java`) **uma vez**, para entender o que acontece por baixo.

## 2. IntelliJ IDEA Community

IDE oficial do curso: <https://www.jetbrains.com/idea/download/> — role a página até **IntelliJ IDEA Community Edition** (gratuita e de código aberto; a Ultimate é paga e desnecessária aqui).

Por que uma IDE desde o início? Porque POO é navegar entre classes: `Ctrl+Click` num tipo abre a classe, e gerar construtores e getters na mão é perda de tempo.

Atalhos que valem ouro:

| Atalho | O que faz |
|--------|-----------|
| `psvm` + `Tab` | Escreve o `public static void main` inteiro |
| `sout` + `Tab` | Escreve `System.out.println()` |
| `Alt + Insert` | Gera construtor, getters/setters, `toString`, `equals`/`hashCode` |
| `Ctrl + Alt + L` | Formata e indenta o arquivo |
| `Shift + F6` | Renomeia (variável, método, classe) em todos os lugares de uma vez |
| `Ctrl + Click` | Vai até a declaração do que você clicou |
| `Shift` `Shift` | Busca qualquer coisa no projeto |

> 💡 **Alternativa:** VS Code + o pacote **Extension Pack for Java** (Microsoft). Funciona bem, mas os atalhos acima mudam — as aulas citam os do IntelliJ.

### Criando um projeto no IntelliJ

*New Project* → **Java** → JDK 21 → sem *build system* (escolha **IntelliJ**) → *Create*. Seus arquivos ficam em `src/`; o botão ▶️ ao lado do `main` executa.

## 3. Git configurado

Você já fez isso no [curso de Git e GitHub](https://github.com/jreluiz/curso-git-github). Confira:

```bash
git --version
git config --list      # user.name e user.email devem aparecer
```

## 4. O repositório de exercícios

Crie no GitHub o repositório **`exercicios-java-poo`** (público, com README) e clone na sua máquina. É nele que **todas** as aulas serão entregues, uma pasta por aula.

Crie também um arquivo `.gitignore` na raiz dele com este conteúdo — sem ele, você vai versionar bytecode compilado e sujar o repositório:

```gitignore
*.class
out/
bin/
target/
.idea/
*.iml
.vscode/
.DS_Store
```

## ✅ Checklist final

- [ ] `java -version` **e** `javac -version` respondem;
- [ ] `java Teste.java` imprime `funciona!`;
- [ ] IntelliJ IDEA Community instalado e abrindo um projeto Java;
- [ ] `git config --list` mostra seu nome e e-mail;
- [ ] Repositório `exercicios-java-poo` criado, clonado, com `.gitignore` e com push funcionando.

---

🏠 [Voltar ao início](../README.md)
