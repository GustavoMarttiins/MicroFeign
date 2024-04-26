# README - Serviços de Pessoa e Boleto

Este README contém informações sobre como configurar e acessar os serviços de Pessoa e Boleto localmente.

## Requisitos:

- Java v17
- MySQL
- Postman.

## Configuração do Banco de Dados:

Antes de executar os serviços, é necessário configurar as informações do `application.yml` para o Banco de Dados, alterando o `username` e o `password`

## Acesso ao Swagger:
Após configurar o banco de dados, você pode acessar a interface do Swagger para cada serviço localmente. Certifique-se de que ambos os serviços estão em execução e, em seguida, acesse as seguintes URLs em seu navegador:

Para o Serviço de Pessoa: http://localhost:8080/swagger-ui.html

Para o Serviço de Boleto: http://localhost:8000/swagger-ui.html

# Observações:
Importa o arquivo `RequisitosPostman` no seu postman para ter acesso a todos endpoints para teste.

Certifique-se de alterar o nome de usuário e senha do banco de dados nos arquivos application.yml de acordo com suas configurações locais.
Reinicie os serviços após fazer qualquer modificação nos arquivos application.yml para garantir que as alterações tenham efeito.

