# Serviço de autenticação.

(MVP) - cadastrar usuário no banco de dados e geração de tokens de acesso a API.

Responsável por cadastrar usuário e gerar token para ser usado na api de weather para fazer a subscrição.

API:

POST **/auth/signup** - Cadastro do usuário.

Body:
```
{
    "username" : "jhonatanedward",
    "email" : "jhonatanedsp@gmail.com",
    "password" : "1234"
}
```

POST **/auth/signin** - Login do usuário com e-mail ou username.
```
{
    "username" : "jhonatanedward",
    "password" : "1234"
}
```

GET **/auth/public-key - Retorna a chave pública que deve ser utilizada pelo consumirdores da API de autenticação para validar a autenticidade do token.


# Subscription Service

(MPV) - Este serviço será responsável pela assinatura do cliente, inicialmente vamos disponibilizar a possibilidade de criar uma assinatura, com status pendente criando um link de pagamento na adquirente, e a possbilidade de consultar esta assinatura para verificar o status atual.

Design API: 

POST **/v1/subscription** - Cria subscrição do usuário.
Body
```
{
    "user_id": "12345",
    "email": "teste@exemplo.com"
}
```

GET **/v1/subscription** - Consulta subscrição do usuário.
```
{
    "status": "success",
    "message": "Ok",
    "data": {
        "id": "ea12c854-0050-462e-9c22-8d240487e74b",
        "user_id": "12345",
        "plan_type": "FREE",
        "external_subscription_id": "sub_7456762787906756008",
        "checkout_url": "https://mock-checkout.com/session?id=sub_7456762787906756008",
        "status": "PENDING",
        "free_plan": true
    }
}
```

# Weather service SAAS.

(MVP) - Disponibilizar os planos para o usuário de acordo com o token.


Acessar POSTGRESQL...

docker exec -it weather-saas-db-1  psql -U user -d subscription_db




## TRADE-OFFS:

Lógica de processamento de pagamento acoplada ao subscription.
