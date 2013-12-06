This is an example to reproduce a problem while using JAX-B to generate code from a schema.
The problem is caused by a nested </choice> combined with </any> element.

This schema snipet

    <xs:choice maxOccurs="unbounded">
        <xs:choice minOccurs="0" maxOccurs="3">
            <xs:element name="Credit" type="CreditType" form="qualified"/>
            <xs:element name="GiftCard" type="GiftCardType" form="qualified"/>
        </xs:choice>
        <xs:any namespace="##other" processContents="lax"/>
    </xs:choice>

Generates this code

    @XmlElementRefs({
        @XmlElementRef(name = "GiftCard", namespace = "http://order.example.com", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Credit", namespace = "http://order.example.com", type = JAXBElement.class, required = false)
    })
    @XmlAnyElement(lax = true)
    protected List<Object> creditOrGiftCardOrAny;

Because the @XmlElementRef is of type JAXBElement, JAX-B cannot find which XSD type to bind.
The error is:

    unable to marshal type "com.example.order.CreditType" as an element because it is missing an @XmlRootElement annotation]

Do you know any workaround for this?

