import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ExpressionEvaluatorTest {

    private final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    @Test
    public void testBasicOperations() {
        assertEquals(7, evaluator.evaluate("3 + 4"), 0.001);
        assertEquals(-1, evaluator.evaluate("3 - 4"), 0.001);
        assertEquals(12, evaluator.evaluate("3 * 4"), 0.001);
        assertEquals(2, evaluator.evaluate("8 / 4"), 0.001);
        assertEquals(9, evaluator.evaluate("3 ^ 2"), 0.001);
    }

    @Test
    public void testParentheses() {
        assertEquals(14, evaluator.evaluate("(3 + 4) * 2"), 0.001);
    }

    @Test
    public void testFunctions() {
        assertEquals(0, evaluator.evaluate("sin(0)"), 0.001);
        assertEquals(1, evaluator.evaluate("cos(0)"), 0.001);
        assertEquals(2, evaluator.evaluate("sqrt(4)"), 0.001);
    }

    @Test
    public void testVariables() {
        // Создаем ввод с значением переменной "x"
        String input = "5\n"; // Имитация ввода значения для переменной x
        InputStream originalSystemIn = System.in; // Сохраняем оригинальный поток ввода
        System.setIn(new ByteArrayInputStream(input.getBytes())); // Подменяем поток ввода

        try {
            assertEquals(10, evaluator.evaluate("2 * x"), 0.001);  // Если x = 5, то 2 * 5 = 10
        } finally {
            System.setIn(originalSystemIn);
        }
    }

    @Test
    public void testInvalidExpression() {
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate("3 + "));
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate("3 + (4 * 2"));
        assertThrows(IllegalArgumentException.class, () -> evaluator.evaluate("3 / 0"));
    }
}
