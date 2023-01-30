# Forwork

### Gerenciador de tarefas, Departamentos e Usuarios

**Forwork possibilita a monitoração de tarefas cadastrando o tempo gasto e usuario que executou**

#### Tecnologias utilizadas:

- Java 17
- Spring boot 3
- Postgres / Spring data JPA para camada de persistencia
- Flyway para criaçao do banco de dados
- Swagger para documentação 
- Docker para conteiner da aplicação e do banco de dados

#### Rodando o sistema
- ***Com docker:***
**Na pasta raiz do projeto, digite no terminal o comando:**
 ```docker-compose up```
- ***Nativamente:*** **Ter o postgres rodando na porta 5432, executar o arquivo .jar**


#### Documentação

**Após iniciar o sistema, a documentação com os endpoints pode ser visualizada no endereço:**
```
hhtp://localhost:8080/docs
```