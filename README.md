# desafio-conexa

para executar o projeto em um docker basta ter o docker instalado e rodar     **docker-compose up -d --build**  

curs utilizados:

      curl --location 'http://localhost:9090/api/v1/signup' \
      --header 'Content-Type: application/json' \
      --data-raw '{
        "email": "medic2o@email.com",
        "senha": "teste111",
        "confirmacaoSenha": "teste111",
        "especialidade": "Cardiologista",
        "cpf": "438.995.278-10",
        "dataNascimento": "10/03/1980",
        "telefone": "(21) 3232-6565"
      }'


    curl --location 'http://localhost:9090/api/v1/login' \
    --header 'Content-Type: application/json' \
    --data-raw ' {
        "email": "medic2o@email.com",
        "senha": "teste111"
      }'


      curl --location 'http://localhost:9090/api/v1/attendance' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6Im1lZGljMm9AZW1haWwuY29tIiwiZXhwIjoxNzMzNzI2NjMzfQ.u8uaJS_oEI_pShTu_pjSCTs783LAcoaaXq0csu-C50g' \
    --header 'Content-Type: application/json' \
    --data '{
      "dataHora": "2021-08-05 09:00:00",
      "paciente": {
        "nome": "João Castro",
        "cpf": "438.995.278-10"
      }
    }'


    curl --location --request POST 'http://localhost:9090/api/v1/logoff' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6Im1lZGljMm9AZW1haWwuY29tIiwiZXhwIjoxNzMzNzI2NTkxfQ.qhE0zHku_bCpBZ6j7bondNbLd7bhQRfFdgoou_JikMc'
