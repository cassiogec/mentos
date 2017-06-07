# mentos

- Tutorial para executar arquivos Linha de Comando:
1) Compile todos os projetos com IDE Netbeans

Para Executar Servidor
2) Entre na pasta Servidor/build/web/WEB-INF/

UDP:
3) Execute comando java -classpath "lib/*:classes/." br.com.servidor.MainUDPService passando dois parametros inteiros, Atualização e Cilos respectivamente

TCP: 
3) Execute comando java -classpath "lib/*:classes/." br.com.servidor.TCPService

Executar Clientes

UDP
* Ajuste arquivo UDPClienteSimulador nas linhas 58 e 61 para pegar o caminho completo até a pasta rotas no projeto, e compile o projeto novamente. Se execução for feita pela IDE, não há a necessidade desse ajuste.
2) Entre na pasta Cliente/Java/UDPClienteSimulador/dist/
3) Execute Comando java -classpath "lib/*:classes/." -jar UDPClienteSimulador.jar

TCP
2) Entre na pasta Cliente/Java/TCPClient/dist/
3) Execute Comando java -classpath "lib/*:classes/." -jar TCPClient.jar

- Tutorial executar Servidor Rest E Soap
1) Abra o projeto Servidor no NetBeans
2) Verifique se a versão do Glassfish instalada na IDE é a 4.1.0 (Existe um bug na versão 4.1.1)
3) Clique com o botão direito no Projeto e clique em implantar
- Acesse localhost:4848 para ver se o glassfish esta funcionando

LOGS:
Serviços Rest e Soap:
Por se utilizar do GlassFish, os logs estará dentro da pasta de instalação do mesmo, seguidos dos caminhos glassfish/domains/domain1/config/ nos arquivos logRest.txt ou logSoap.txt
TCP:

