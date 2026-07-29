package org.example.localization;

public sealed interface Part permits LiteralPart, ArgumentPart{
    void append(StringBuilder builder, String[] args);
    void append(StringBuilder builder, String arg);
    void append(StringBuilder builder);
    String toString();
}