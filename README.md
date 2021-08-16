# Wooper-Challenge Android

## 🚀 Introdução
A aplicação consiste em consumir um seviço REST API que possuí uma listagem de eventos com vários detalhes, os eventos são listados na tela inicial e após ser pressionado
entra em uma nova janela com mais detalhes do evento incluíndo sua localização utilizando a API do google maps.  
Nessa mesma tela é possível realizar o check-in no evento, enviando um POST para a API e retornando um feedback ao usuário caso esse POST tenha sido realizado com sucesso ou não.  

## Arquitetura
Para realização do teste utilizei o padrão de projeto MVVM Clean Architecture, respeitando todos os requisitos como recomenda o Google.
![alt text](https://www.objective.com.br/wp-content/uploads/2020/01/fluxo-de-comunicacao.png)
## Bibliotecas utilizadas
<!--ts-->
   * [Lottie](https://github.com/airbnb/lottie-android) - Utilizado para fazer uma animação quando o POST é realizado com sucesso
   * [Maps](com.google.android.gms:play-services-maps:17.0.1) - Para localizar a latitude/longitude e mostrar no google maps
   * [Gson e Retrofit](https://square.github.io/retrofit/) - Requisição REST API e formatção de arquivo JSON
   * [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Injeção de dependências
   * [Glide](https://github.com/bumptech/glide) - Lidar com requisição e ajuste de imagens
   * [Mock webserver](https://mvnrepository.com/artifact/com.squareup.okhttp/mockwebserver/1.2.1) - Teste unitário para as requisições REST
   * [Truth](https://truth.dev/) - Validar testes unitários
   * [Navigation Architecture](https://developer.android.com/jetpack/androidx/releases/navigation) - Para lidar com transições de telas e animações entre elas
   * [Coroutines](https://developer.android.com/kotlin/coroutines?gclid=CjwKCAjwmeiIBhA6EiwA-uaeFZEI5aybL4QQskanMDcRY9w8y6c4i06YCl8p1BTSgaEFnQaRX_udPRoCBn8QAvD_BwE&gclsrc=aw.ds) - Realiza tarefas em background
   * [ViewModel e LiveData](https://developer.android.com/jetpack/androidx/releases/lifecycle)
   * [Databinding]
<!--te-->
