package com.grupo3.Model.enums;

public enum Pagamento {
	Boleto("Boleto"),
    Cartao("Cartao");

    private String pagamento;
    private Pagamento(String pagamento){
        this.pagamento = pagamento;
    }
}
