package org.example.localization;

public final class LiteralPart implements Part{
    private final String text;

    public LiteralPart(String text){
        this.text = text;
    }

    @Override
    public void append(StringBuilder builder, String[] args){
        builder.append(this.text);
    }

    @Override
    public void append(StringBuilder builder, String arg){
        builder.append(this.text);
    }

    @Override
    public void append(StringBuilder builder){
        builder.append(this.text);
    }

    @Override
    public String toString(){
        return this.text;
    }
}