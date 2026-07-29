package org.example.localization;

public final class ArgumentPart implements Part{
    private final int index;

    public ArgumentPart(int index){
        this.index = index;
    }

    @Override
    public void append(StringBuilder builder, String[] args){
        if(this.index < 0 || this.index >= args.length){
            return;
        }

        builder.append(args[index]);
    }

    @Override
    public void append(StringBuilder builder, String arg){
        if(this.index != 0){
            return;
        }

        builder.append(arg);
    }

    @Override
    public void append(StringBuilder builder){
        // nothing
    }

    @Override
    public String toString(){
        return "%" + this.index;
    }
}