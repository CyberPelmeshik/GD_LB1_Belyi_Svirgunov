import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите уравнение в любом формате (коэффициенты могут быть в любом порядке):");
        String equation = scanner.nextLine();

        // Удаление всех пробелов из строки
        equation = equation.replaceAll("\\s", "");

        // Извлечение коэффициентов a, b и c из уравнения
        Pattern pattern = Pattern.compile("([-+]?\\d*\\.?\\d*x\\^2|[-+]?\\d*\\.?\\d*x|[-+]?\\d+\\.?\\d*)");
        Matcher matcher = pattern.matcher(equation);

        double a = 0, b = 0, c = 0;

        while (matcher.find()) {
            String match = matcher.group();

            if (match.endsWith("x^2")) {
                if (match.equals("+x^2")) {
                    a += 1.0;
                }
                else if (match.equals("-x^2")){
                    a -= 1.0;
                }
                else {
                    a += parseCoefficient(match);
                }
            } else if (match.endsWith("x") && !match.contains("^")) {
                if (match.equals("+x")) {
                    b += 1.0;
                }
                else if (match.equals("-x")){
                    b -= 1.0;
                }
                else {
                    b += parseCoefficient(match);
                }
            } else {
                c += parseCoefficient(match);
            }
        }
        // Вычисление дискриминанта
        double discriminant = b * b - 4 * a * c;


        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("Дискриминант = " + discriminant);

        // Проверка знака дискриминанта
        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Уравнение имеет два корня:");
            System.out.println("x1 = " + root1);
            System.out.println("x2 = " + root2);
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            System.out.println("Уравнение имеет один корень:");
            System.out.println("x = " + root);
        } else {
            System.out.println("Уравнение не имеет действительных корней.");
        }
    }

    // Метод для извлечения числового значения коэффициента из строки
    private static double parseCoefficient(String str) {
        // Для случая x^2 или x, без указания коэффициента
        if (str.equals("x^2") || str.equals("x")) {
            return 1.0; // По умолчанию коэффициент равен 1
        } else if (str.equals("-x^2") || str.equals("-x")) {
            return -1.0; // Для отрицательных случаев без числа перед x
        }

        // Удаление символов x и ^2 для извлечения числовых значений
        if (str.contains("x^2")) {
            str = str.replace("x^2", "");
        } else if (str.contains("x")) {
            str = str.replace("x", "");
        }

        // Обработка + и - как символов начала числа, если они есть
        if (str.startsWith("+")) {
            str = str.substring(1);
        }

        return Double.parseDouble(str);
    }
}
