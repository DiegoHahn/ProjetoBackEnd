# Projeto Previsão do Tempo
Projeto intermediario da disciplina Back-End com o tema de previsão do tempo.

## Descrição
O projeto desenvolvido é uma API que fornece uma lista com a previsão do tempo de uma determinada cidade, em um periodo de dias.
Para isso são utilizadas duas API's externas, uma para obter as coordenadas geográficas da cidade informada e outra para obter a previsão do tempo.

## API's Utilizadas
- [Weather Forecast API](https://open-meteo.com/en/docs) - API de previsão do tempo
- [Geocoding API](https://open-meteo.com/en/docs/geocoding-api) - API de geolocalização


## Como usar
Para utilizar a API, é necessário realizar um POST na rota `/coordenadas` com o seguinte corpo:
```json
{
  "cidade": "Criciúma",
  "forecastDays": 3
}
```
Onde: o campo `cidade` é uma `String` com o nome da cidade que deseja saber a previsão do tempo, e o campo `forecastDays` é um `int` com a quantidade de 
dias que deseja saber a previsão. Sendo que por padrão da API Weather Forecast esse valor de forecastDays pode ser somente ```1, 3, 7, 14 ou 16``` dias.

Após realizar o POST, a API irá retornar uma mensagem informando se a requisição foi bem sucedida ou não.
Caso tenha sido bem sucedida, você pode realizar um GET na rota `/previsao` para obter a previsão do tempo da cidade informada.

## Como funciona
A API irá recebe a cidade e a quantidade de dias informada no corpo do POST como no exemplo acima, então o serviço `CoordenadasService` realiza uma requisição para a API Geocoding,
baseado apenas no nome da cidade a API Geocoding devolve uma latitude e longitude, então o `CoordenadasService` pega essas duas informações, adiciona o `forecastDays` e cria um objeto `PrevisaoReq`.
Esse objeto é enviado para o serviço `PrevisaoService` que faz uma requisição para a API Weather Forecast, nessa requisição poderiam ser passados varios parametros que devolveriam mais informações (como velocidade do vento, umidade, etc), mas para simplificar,
os paramêtros que devolvem informações são fixos (temperatura maxima e minima probabilidade e volume de chuva). A resposta da API Weather Forecast é um objeto com varias informações, mas apenas um objeto dentro desse JSON resposta é útilizado,
então a classe `PrevisaoResp` serve para desserializar essa resposta. O `PrevisaoService` então pega as informações uteis do objeto `PrevisaoResp` e cria um objeto `PrevisaoMensagem`, para cada dia do forecastDays é criado uma `PrevisaoMensagem` isso foi feito
apenas para facilitar a visualização no navegador (cada dia é uma linha separada), essas mensagens são adicionadas a uma `PrevisaoCache` que é apenas uma lista de `PrevisaoMensagem` para gravar na memória e devolver ao usuario no endpoint.
