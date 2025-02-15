# Fórum Hub 💻

## Descrição

O Fórum hub é um desafio proposto pela a alura na conclusão do programa Oracle ONE. O projeto é utilizado para consolidar conhecimento e replicar a parte de Back-End do forum Alura.

## Funcionalidades

- **Criação de Tópicos:** Usuários registrados podem iniciar discussões sobre temas específicos de cursos, fornecendo um título e uma mensagem detalhada.
- **Listagem de Tópicos:** Exibição de todos os tópicos disponíveis para navegação e participação.
- **Visualização de Tópico:** Permite a visualização completa e detalhada de cada tópico individual.
- **Autenticação e Autorização:** Garante que apenas usuários autenticados possam criar, modificar ou remover tópicos, assegurando a segurança dos dados.
- **Criação de Respostas:** Capacidade para usuários cadastrados responderem aos tópicos existentes com suas contribuições.
- **Listagem de Respostas:** Apresenta todas as respostas disponíveis para cada tópico, facilitando o acompanhamento das discussões.
- **Visualização de Tópico:** Oferece uma visualização detalhada de cada resposta específica dentro de um tópico.
- **Autenticação e Autorização:** Restringe a criação, edição e exclusão de respostas apenas a usuários autenticados, mantendo a integridade e a segurança dos dados do fórum.
- **Registro de Usuários:** Permite que novos usuários se cadastrem no sistema, com a emissão de tokens JWT para autenticação segura.

## Regra de Negócio

1. **Usuários Cadastrados:** Apenas usuários registrados podem criar, editar ou deletar tópicos e respostas.
2. **Autenticação:** Utilização de tokens Bearer para autenticação nas requisições.
3. **Segurança:** Apenas o autor do tópico ou resposta pode editá-lo ou deletá-lo.
4. **Estrutura do Tópico:** Cada tópico deve ter um título, uma mensagem, pertencer a um curso e ter uma data de criação gerada automaticamente.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.3.0**
- **MySQL**
- **Hibernate**
- **JWT (JSON Web Token)**
- **Lombok**
- **Flyway**
- **Spring Security**
- **Spring Data JPA**
- **Springdoc OpenAPI**

## Endpoints Principais

![SpringDocs!](https://github.com/Gerfy1/ForumHub/blob/master/.mvn/wrapper/t2.png?raw=true)


- **Registrar Usuário:** `POST /usuarios`
- **Atualizar Usuário:** `POST /usuarios`
- **Registrar Usuário:** `DELETE /usuarios`
- **Autenticar Usuário:** `POST /login`
- **Criar Tópico:** `POST /topicos`
- **Listar Tópicos:** `GET /topicos`
- **Visualizar um Tópico Específico:** `GET /topicos/{id}`
- **Editar Tópico:** `PUT /topicos/{id}`
- **Deletar Tópico:** `DELETE /topicos/{id}`
- **Criar Resposta:** `POST /topicos/{id}/respostas`
- **Listar Respostas de um Tópico:** `GET /topicos{id}/respostas`
- **Visualizar Respostas de um Tópico Específico:** `GET /topicos/{id}/respostas/{id2}`
- **Editar Resposta:** `PUT /topicos/{id}/respostas/{id2}`
- **Deletar Resposta:** `DELETE /topicos/{id}/respostas/{id2}`

## Diagrama do banco de dados

![Diagrama do Banco de Dados](https://raw.githubusercontent.com/Gerfy1/ForumHub/master/.mvn/wrapper/T.png)
