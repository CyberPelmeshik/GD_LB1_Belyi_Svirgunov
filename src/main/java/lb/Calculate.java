package lb;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {

    private static String equation_;
    private static String r;

    Calculate(String args) {
        Calculate.equation_ = args;
    }

    public static void main(String[] args) {

        String equation = equation_;
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
        if (a == 0) {
            r = "Уравнение не является квадратным, так как коэффициент a равен 0.";
        }
        else {
            // Вычисление дискриминанта
            double discriminant = b * b - 4 * a * c;


            r = "a = " + a + "\n b = " + b + "\n = " + c + "\n Дискриминант = " + discriminant;

            DecimalFormat df = new DecimalFormat("#.00");


            // Проверка знака дискриминанта
            if (discriminant > 0) {

                double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);

                String formatedRoot1 = df.format(root1);
                String formatedRoot2 = df.format(root2);

                r = "\nУравнение имеет два корня:" + "\n x1 = " + formatedRoot1 + "\n x2 = " + formatedRoot2;

            } else if (discriminant == 0) {

                double root = -b / (2 * a);

                if (root == -0) {
                    root = 0; // Присвоить значение 0, если корень равен -0

                }

                    r = "Уравнение имеет один корень:" + "\nx = " + root;

            } else {
                r = "Уравнение не имеет действительных корней.";

            }
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

    public String getRet(){
        return r;
    }
} 