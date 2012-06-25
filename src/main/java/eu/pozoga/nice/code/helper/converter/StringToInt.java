package eu.pozoga.nice.code.helper.converter;

/**
 *
 * @author Sebastian Pożoga
 */
@Converter(from=String.class,to=int.class)
public class StringToInt extends AbstractConverter<String, Integer> {

    @Override
    public Integer convert(String object) {
        return Integer.parseInt(object);
    }
    
}
