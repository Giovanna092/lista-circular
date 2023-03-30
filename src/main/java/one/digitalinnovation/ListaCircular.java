package one.digitalinnovation;

public class ListaCircular<T> {

    private No<T> cabeca; //neste caso a cabeca é o ultimo elemento da lista
    private No<T> cauda; //neste caso a cauda é o primeiro elemento da lista
    private int tamanhoLista;

    public ListaCircular() {
        this.cauda = null;
        this.cabeca = null;
        this.tamanhoLista = 0;
    }

    //adiciona um novo No sempre no começo (cauda)
    public void add(T conteudo) {
        No<T> novoNo = new No<>(conteudo);
        if (this.isEmpty()) { //se a lista estiver vazia o elemento adicionado vira a cauda e a cabeça
            this.cabeca = novoNo;
            this.cauda = this.cabeca;
            this.cabeca.setNoProximo(cauda);
        } else { //novo nó é inserido atras da antiga cauda
            novoNo.setNoProximo(this.cauda);
            this.cabeca.setNoProximo(novoNo);
            this.cauda = novoNo;
        }
        this.tamanhoLista++;
    }

    //remove um elemento atraves de um indice
    public void remove(int index) {
        if (index >= this.tamanhoLista)
            throw new IndexOutOfBoundsException("O indice é maior que o tamanho da lista.");
        No<T> noAuxiliar = this.cauda;
        if (index == 0) { //é removido a cauda e setado a posterior a ela como nova cauda
            this.cauda = this.cauda.getNoProximo();
            this.cabeca.setNoProximo(this.cauda);
        } else if (index == 1) { //a cauda precisa parar de apontar para o segundo indica para ele ser removido
            this.cauda.setNoProximo(this.cauda.getNoProximo().getNoProximo());
        } else {
            for (int i = 0; i < index-1; i++) { //percorre a lista pegando o elemento anterior ao que será removido
                noAuxiliar = noAuxiliar.getNoProximo();
            } //agora seta a referencia do nó anterior ao proximo nó do que será removido
            noAuxiliar.setNoProximo(noAuxiliar.getNoProximo().getNoProximo());
        }

        this.tamanhoLista--;
    }

    //informa o conteudo do nó que está em determinado indice
    public T get(int index) {
        return this.getNo(index).getConteudo();
    }

    //apenas para pegar o Nó no indice informado
    private No<T> getNo(int index) {
        if (this.isEmpty())
            throw new IndexOutOfBoundsException("A lista está vazia!");

        if (index == 0) {
            return this.cauda;
        }

        No<T> noAuxiliar = this.cauda;
        for (int i = 0; i < index; i++) {
            noAuxiliar = noAuxiliar.getNoProximo();
        }

        return noAuxiliar;

    }

    public boolean isEmpty() {
        return this.tamanhoLista == 0 ? true : false;
    }

    public int size() {
        return this.tamanhoLista;
    }

    @Override
    public String toString() {
        String strRetorno = "";
        No<T> noAuxiliar = this.cauda;

        for (int i = 0; i < this.size(); i++) {
            strRetorno += "[No{conteudo="+noAuxiliar.getConteudo()+"}]--->";
            noAuxiliar = noAuxiliar.getNoProximo();
        }

        strRetorno += this.size() != 0 ? "(Retorna ao inicio)" : "[]";

        return strRetorno;
    }
}
