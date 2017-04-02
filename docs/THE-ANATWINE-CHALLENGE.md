# The Anatwine Challenge
Write a program in Java (JDK 8 / Maven) and associated unit tests that
can price a basket of fashion goods taking into account some special offers.

The goods that can be purchased, together with their normal prices are
to be stored in a product catalogue and are currently:

-   Jacket – £49.90
-   Trousers – £35.50
-   Shirt – £12.50
-   Tie – £9.50

Current special offers:

-   Trousers have a 10% discount off their normal price this week
-   Buy 2 shirts and get a tie for half price

The program should accept a list of items in the basket and output the
subtotal, the special offer discounts and the final price.

Input should be via the command line in the form
`AnatwineBasket item1 item2 item3 ...`

For example:

    AnatwineBasket Jacket Trousers Tie

Output should be to the console, for example:

    Subtotal: £94.90
    Trousers 10% off: -£3.55
    Total: £91.35
    
If no special offers are applicable the code should output, for example:

    Subtotal: £12.50
    (No offers available)
    Total: £12.50

The code and design should meet these requirements, but be sufficiently
flexible to allow future changes to the product catalogue and/or discounts
applied (i.e. ideally without the need to add or update significant Java code).

The code should be well structured, commented, have error handling
and be tested. 

You may use libraries or frameworks if you feel it enhances your solution. 
