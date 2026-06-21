import java.util.HashMap;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final Map<Integer, Customer> customers = new HashMap<>();

    public CustomerRepositoryImpl() {
        customers.put(1, new Customer(1, "Riya"));
        customers.put(2, new Customer(2, "Kabir"));
    }

    @Override
    public Customer findCustomerById(int id) {
        return customers.get(id);
    }
}
