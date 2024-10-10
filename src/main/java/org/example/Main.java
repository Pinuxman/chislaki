package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main{

    // Метод прогонки для решения системы трёхдиагональных уравнений
    public static double[] solve(double[] a, double[] b, double[] c, double[] d) {
        int n = b.length;
        double[] x = new double[n];
        double[] alpha = new double[n];
        double[] beta = new double[n];

        // Прямой проход
        alpha[0] = -c[0] / b[0];
        beta[0] = d[0] / b[0];

        for (int i = 1; i < n; i++) {
            double denominator = b[i] + a[i] * alpha[i - 1];
            alpha[i] = -c[i] / denominator;
            beta[i] = (d[i] - a[i] * beta[i - 1]) / denominator;
        }

        // Обратный проход
        x[n - 1] = beta[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            x[i] = alpha[i] * x[i + 1] + beta[i];
        }

        return x;
    }
    public static double[] check(double[] a, double[] b, double[] c, double[] d, double[] x){
        int n = a.length;
        double[] check = new double[3 * n];
        for (int i = 1; i < n; i++){
            check[i - 1] = x[i - 1] * a[i] - d[i];
        }
        for (int i = n; i < 2 * n; i++){
            check[i - 1] = x[i - n] * b[i - n] - d[i - n];
        }
        for (int i = 2 * n; i < 3 * n; i++){
            check[i - 1] = x[i - (n * 2)] * b[i -(n * 2)] - d[i -(n * 2)];
        }
        return check;
    }
    public static void main(String[] args) {

        double[] a = {0, -7., 4., -4., -1.};  // нижняя диагональ
        double[] b = {16., -16., 12., 12., 7.};    // главная диагональ
        double[] c = {-8., 5., 3., -7., 0}; // верхняя диагональ
        double[] d = {0, -123., -68., 104., 20};   // вектор правых частей

        double[] solution = solve(a, b, c, d);

        System.out.println("Решение системы:");
        for (double v : solution) {
            System.out.printf("%.15f\n", v);
        }
//        double[] ch = check(a, b, c, d, solution);
//        System.out.println("check: ");
//        for (double v : ch) {
//            System.out.println(v);
//       }


    }
}
