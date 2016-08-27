# so-you-think-you-know-java-strings

Exploring the relationship between 16-bit char types and UTF-8 bytes in Java.  The APIs make it simple to convert between Java Strings and byte arrays by specifying the char encoding.  For web development and text processing you usually don't need to think about character encoding details, but what if you're writing a font rendering engine?  In this case, to properly support Unicode and UTF-8, you need to map code points to glyphs.

Since Unicode code points can have 32 bits of precision, they will not fit in a 16 bit Java char type.  To handle Unicode Supplementary Characters, Java added code point-based APIs in JDK 1.5.  This demo was put together after reading the excellent article [Supplementary Characters in the Java Platform](http://www.oracle.com/us/technologies/java/supplementary-142654.html).
