package Service;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ApiConsumo {

    //Criamos um objeto gsonbuilder para desserializar o json que queremos
    Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .create();

    private final String endereco = "https://v6.exchangerate-api.com/v6/b227348e6fb2bbfb55d0c98f/pair/";

    public void executarRequisicao(int escolha, double valor) {
            String moedaOrigem = "";
            String moedaDestino = "";

            switch (escolha) {
                case 1:
                    moedaOrigem = "BRL";
                    moedaDestino = "ARS"; // Peso argentino
                    break;
                case 2:
                    moedaOrigem = "ARS";
                    moedaDestino = "BRL"; // Real brasileiro
                    break;
                case 3:
                    moedaOrigem = "BRL";
                    moedaDestino = "USD"; // Dólar
                    break;
                case 4:
                    moedaOrigem = "USD";
                    moedaDestino = "BRL"; // Real brasileiro
                    break;
                case 5:
                    moedaOrigem = "BRL";
                    moedaDestino = "EUR"; // Euro
                    break;
                case 6:
                    moedaOrigem = "EUR";
                    moedaDestino = "BRL"; // Real brasileiro
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    return;
            }

        String url = endereco + moedaOrigem + "/" + moedaDestino + "/" + valor;
            double valorConvertido = 0.0;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Extrai o valor convertido do JSON (campo "conversion_result")
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
            valorConvertido = jsonObject.get("conversion_result").getAsDouble();

        } catch (IllegalArgumentException e) {
            System.out.println("Erro no endereço, verifique a URL.");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Valor "+ valor + " [" + moedaOrigem + "] corresponde ao valor final de =>>> " + valorConvertido + " [" + moedaDestino + "]");
    }

}
