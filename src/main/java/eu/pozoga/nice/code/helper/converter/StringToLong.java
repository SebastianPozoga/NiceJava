package eu.pozoga.nice.code.helper.converter;

/**
 *
 * @author Sebastian Pożoga
 */
@Converter(from=String.class,to=Long.class)
public class StringToLong extends AbstractConverter<String, Long> {

    @Override
    public Long convert(String object) {
        return Long.parseLong(object);
    }
    
}
