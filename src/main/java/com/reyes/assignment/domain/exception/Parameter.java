package com.reyes.assignment.domain.exception;

/**
 * Represents a name-value pair parameter associated with a domain exception.
 * <p>
 * This class encapsulates contextual information about parameters that led to
 * or are related to an exception condition. It provides a structured way to
 * pass parameter details when throwing {@link DomainException} instances.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 * Parameter accountParam = Parameter.of("accountId", "ACC123");
 * throw new InvalidInputException("INVALID_ACCOUNT", accountParam);
 * </pre>
 * </p>
 *
 * @see DomainException
 */
public class Parameter {
    private String name;
    private String value;

    private Parameter() {
    }

    public static Parameter of(String name, String value) {
        Parameter parameter = new Parameter();
        parameter.name = name;
        parameter.value = value;
        return parameter;
    }

}
