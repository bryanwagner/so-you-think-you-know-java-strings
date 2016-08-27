# so-you-think-you-know-java-strings
Exploring the relationship between 16-bit Java chars and UTF-8 bytes.  The APIs make it simple to convert between Java Strings and byte arrays by specifying the char encoding, but what if you're writing a font rendering engine?  In this case, to properly support UTF-8, you need to map code points to glyphs.
