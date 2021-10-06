# Projeto final referente a matéria de Microservices & Serverless Architecture
## Curso: MBA em Full Stack Developer

**Aplicação:** fatura
	
**Visão geral:** Essa aplicação tem por objetivo registrar movimentações de fatura.
Sendo assim, nela temos os request para os seguintes objetivos:
- Criar uma fatura;
- Consultar uma fatura atráves da conta corrente, mês e ano; 
- Realizar pagamento de uma fatura através da conta corrente, mês e ano; 

**Requisitos minimos de instalação:**
- Java 11
- Docker desktop
- Maven

**Tecnologias utilizadas:**
- Spring Boot
- Java 11
- Maven
- Junit
- Swagger

**Monitoração:**
- Prometheus

**Segurança:**
- API Gateway

**Disponibilidade:**
- Eureka

**Postman - link para import na plataforma:**
- https://www.getpostman.com/collections/f8eea8546a1af821ef31

**OBS:** Para a execução das aplicações locais, aplicação de fatura **DEPENDE** das aplicações:
- eureka (https://github.com/nataliamilani/eureka) já estar online para receber os registros;
- api-gateway (https://github.com/nataliamilani/api-gateway);
- prometheus (https://github.com/nataliamilani/prometheus);
- debito (https://github.com/nataliamilani/credito);
- credito (https://github.com/nataliamilani/credito);
- conta-corrente (https://github.com/nataliamilani/conta-corrente);

Para subir os containers, basta utilizar o comando "docker-compose up", no diretório raiz das aplicações, após clonar os repositórios na máquina local.
