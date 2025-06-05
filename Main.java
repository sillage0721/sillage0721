import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 class MultiProjectLauncher extends JFrame {

    private static final String[] PROJECT_NAMES = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "全部运行"
    };

    // 日志
    private static class Logger {
        private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        private static PrintWriter logWriter;

        static {
            try {
                logWriter = new PrintWriter(new FileWriter("1034230219.log", true));
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    if (logWriter != null) {
                        logWriter.close();
                    }
                }));
            } catch (IOException e) {
                System.err.println("无法创建日志文件: " + e.getMessage());
            }
        }

        public static void log(String message) {
            String timestamp = dateFormat.format(new Date());
            String logMessage = "[" + timestamp + "] " + message;
            if (logWriter != null) {
                logWriter.println(logMessage);
                logWriter.flush();
            }
            System.out.println(logMessage);
        }
    }

    public MultiProjectLauncher() {
        setTitle("GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JButton btnCalc = new JButton(PROJECT_NAMES[0]);
        JButton btnScore = new JButton(PROJECT_NAMES[1]);
        JButton btnElephant = new JButton(PROJECT_NAMES[2]);
        JButton btnCuboid = new JButton(PROJECT_NAMES[3]);
        JButton btnVehicle = new JButton(PROJECT_NAMES[4]);
        JButton btnAbstract = new JButton(PROJECT_NAMES[5]);
        JButton btnBank = new JButton(PROJECT_NAMES[6]);
        JButton btnAll = new JButton(PROJECT_NAMES[7]);

        panel.add(btnCalc);
        panel.add(btnScore);
        panel.add(btnElephant);
        panel.add(btnCuboid);
        panel.add(btnVehicle);
        panel.add(btnAbstract);
        panel.add(btnBank);
        panel.add(btnAll);

        btnCalc.addActionListener(e -> {
            Logger.log("用户点击了: " + PROJECT_NAMES[0]);
            launchCalculator();
        });
        btnScore.addActionListener(e -> {
            Logger.log("用户点击了: " + PROJECT_NAMES[1]);
            launchScoreCalculator();
        });
        btnElephant.addActionListener(e -> {
            Logger.log("用户点击了: " + PROJECT_NAMES[2]);
            launchElephantInFridge();
        });
        btnCuboid.addActionListener(e -> {
            Logger.log("用户点击了: " + PROJECT_NAMES[3]);
            launchCuboid();
        });
        btnVehicle.addActionListener(e -> {
            Logger.log("用户点击了: " + PROJECT_NAMES[4]);
            launchVehicleTest();
        });
        btnAbstract.addActionListener(e -> {
            Logger.log("用户点击了: " + PROJECT_NAMES[5]);
            launchAbstractVsInterface();
        });
        btnBank.addActionListener(e -> {
            Logger.log("用户点击了: " + PROJECT_NAMES[6]);
            launchBankSystem();
        });
        btnAll.addActionListener(e -> {
            Logger.log("用户点击了: " + PROJECT_NAMES[7]);
            launchAllProjects();
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        JButton btnExit = new JButton("退出");
        btnExit.addActionListener(e -> System.exit(0));
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnExit);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
        Logger.log("多项目启动器已启动");
    }

    private void launchCalculator() {
        new Thread(() -> {
            Logger.log("开始执行计算器项目");
            CalculatorProject.main(new String[]{});
            Logger.log("计算器项目执行完成");
        }).start();
    }

    private void launchScoreCalculator() {
        new Thread(() -> {
            Logger.log("开始执行成绩计算项目");
            ScoreCalculatorProject.main(new String[]{});
            Logger.log("成绩计算项目执行完成");
        }).start();
    }

    private void launchElephantInFridge() {
        new Thread(() -> {
            Logger.log("开始执行大象冰箱项目");
            ElephantInFridgeProject.main(new String[]{});
            Logger.log("大象冰箱项目执行完成");
        }).start();
    }

    private void launchCuboid() {
        new Thread(() -> {
            Logger.log("开始执行长方体项目");
            CuboidProject.main(new String[]{});
            Logger.log("长方体项目执行完成");
        }).start();
    }

    private void launchVehicleTest() {
        new Thread(() -> {
            Logger.log("开始执行交通工具项目");
            VehicleTestProject.main(new String[]{});
            Logger.log("交通工具项目执行完成");
        }).start();
    }

    private void launchAbstractVsInterface() {
        new Thread(() -> {
            Logger.log("开始执行抽象与接口项目");
            AbstractVsInterfaceDemo.main(new String[]{});
            Logger.log("抽象与接口项目执行完成");
        }).start();
    }

    private void launchBankSystem() {
        new Thread(() -> {
            Logger.log("开始执行银行系统项目");
            BankSystemProject.main(new String[]{});
            Logger.log("银行系统项目执行完成");
        }).start();
    }

    private void launchAllProjects() {
        Logger.log("开始同时启动所有项目");
        ExecutorService executor = Executors.newFixedThreadPool(7);

        executor.submit(() -> {
            Logger.log("启动计算器项目");
            CalculatorProject.main(new String[]{});
        });

        executor.submit(() -> {
            Logger.log("启动成绩计算项目");
            ScoreCalculatorProject.main(new String[]{});
        });

        executor.submit(() -> {
            Logger.log("启动大象冰箱项目");
            ElephantInFridgeProject.main(new String[]{});
        });

        executor.submit(() -> {
            Logger.log("启动长方体项目");
            CuboidProject.main(new String[]{});
        });

        executor.submit(() -> {
            Logger.log("启动交通工具项目");
            VehicleTestProject.main(new String[]{});
        });

        executor.submit(() -> {
            Logger.log("启动抽象与接口项目");
            AbstractVsInterfaceDemo.main(new String[]{});
        });

        executor.submit(() -> {
            Logger.log("启动银行系统项目");
            BankSystemProject.main(new String[]{});
        });

        executor.shutdown();

        new Thread(() -> {
            while (!executor.isTerminated()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Logger.log("所有项目执行完成");
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MultiProjectLauncher::new);
    }
}

// 1
class CalculatorProject {
    private double num1;
    private double num2;

    public CalculatorProject(double num1, double num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public double add() {
        return num1 + num2;
    }

    public double subtract() {
        return num1 - num2;
    }

    public double multiply() {
        return num1 * num2;
    }

    public double divide() {
        if (num2 == 0) {
            System.out.println("错误：除数不能为零");
            return Double.NaN;
        }
        return num1 / num2;
    }

    public static void main(String[] args) {
        System.out.println("===== 计算器项目启动 =====");
        CalculatorProject calc = new CalculatorProject(24.5, 7.5);

        System.out.println("数字1: 24.5");
        System.out.println("数字2: 7.5");
        System.out.println("加: " + calc.add());
        System.out.println("减: " + calc.subtract());
        System.out.println("乘: " + calc.multiply());
        System.out.println("除: " + calc.divide());

        System.out.println("\n测试除以零:");
        CalculatorProject zeroCalc = new CalculatorProject(10, 0);
        System.out.println("10 / 0 = " + zeroCalc.divide());

        System.out.println("===== 计算器项目结束 =====");
    }
}

// 2
class ScoreCalculatorProject {
    private double[] scores = new double[50];

    public void generateScores() {
        Random rand = new Random();
        for (int i = 0; i < 50; i++) {
            scores[i] = Math.round(rand.nextDouble() * 100 * 100.0) / 100.0;
        }
    }

    public double getTotal() {
        double total = 0;
        for (double score : scores) {
            total += score;
        }
        return Math.round(total * 100.0) / 100.0;
    }

    public double getAverage() {
        return Math.round((getTotal() / scores.length) * 100.0) / 100.0;
    }

    public static void main(String[] args) {
        System.out.println("===== 成绩计算项目启动 =====");
        ScoreCalculatorProject sc = new ScoreCalculatorProject();
        sc.generateScores();

        System.out.println("随机生成的成绩：");
        for (int i = 0; i < sc.scores.length; i++) {
            System.out.printf("%.2f\t", sc.scores[i]);
            if ((i + 1) % 5 == 0) System.out.println();
        }

        System.out.println("\n总成绩: " + sc.getTotal());
        System.out.println("平均成绩: " + sc.getAverage());
        System.out.println("===== 成绩计算项目结束 =====");
    }
}

// 3
class ElephantInFridgeProject {
    private boolean isFridgeOpen = false;
    private boolean isElephantIn = false;

    public void openFridge() {
        if (!isFridgeOpen) {
            System.out.println("冰箱已打开");
            isFridgeOpen = true;
        }
    }

    public void putElephant() {
        if (isFridgeOpen && !isElephantIn) {
            System.out.println("大象已装入冰箱");
            isElephantIn = true;
        }
    }

    public void closeFridge() {
        if (isFridgeOpen && isElephantIn) {
            System.out.println("冰箱已关闭");
            isFridgeOpen = false;
        }
    }

    public static void main(String[] args) {
        System.out.println("===== 大象冰箱项目启动 =====");
        ElephantInFridgeProject operation = new ElephantInFridgeProject();
        operation.openFridge();
        operation.putElephant();
        operation.closeFridge();
        System.out.println("===== 大象冰箱项目结束 =====");
    }
}

// 4
class CuboidProject {
    private double length;
    private double width;
    private double height;

    public CuboidProject() {
        this.length = 5.0;
        this.width = 5.0;
        this.height = 5.0;
    }

    public CuboidProject(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getVolume() {
        return length * width * height;
    }

    public static void main(String[] args) {
        System.out.println("===== 长方体项目启动 =====");
        CuboidProject cuboid0 = new CuboidProject();
        System.out.println("默认长方体体积: " + cuboid0.getVolume());

        Scanner sc = new Scanner(System.in);
        System.out.print("请输入长度: ");
        double a = sc.nextDouble();
        System.out.print("请输入宽度: ");
        double b = sc.nextDouble();
        System.out.print("请输入高度: ");
        double c = sc.nextDouble();

        CuboidProject cuboid = new CuboidProject(a, b, c);
        System.out.println("长方体体积: " + cuboid.getVolume());
        System.out.println("===== 长方体项目结束 =====");
        sc.close();
    }
}

// 5
interface VehicleInterface {
    void startEngine();
    default void showInfo() {
        System.out.println("=== 交通工具基本信息 ===");
    }
}

abstract class CarBase implements VehicleInterface {
    private String brand;
    protected int maxSpeed;

    public CarBase(String brand, int maxSpeed) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
    }

    public abstract void chargeEnergy();

    @Override
    public void startEngine() {
        System.out.println(brand + "启动中...");
    }

    public final void safetyCheck() {
        System.out.println("正在进行安全系统检测");
    }

    public String getBrand() {
        return brand;
    }
}

class ElectricCarImpl extends CarBase {
    private int batteryCapacity;

    public ElectricCarImpl(String brand, int maxSpeed, int battery) {
        super(brand, maxSpeed);
        this.batteryCapacity = battery;
    }

    @Override
    public void chargeEnergy() {
        System.out.println(getBrand() + "正在快充，电池容量：" + batteryCapacity + "kWh");
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("类型: 电动车\n最大速度: " + maxSpeed + "km/h");
    }
}

class GasolineCarImpl extends CarBase {
    private double engineDisplacement;

    public GasolineCarImpl(String brand, int maxSpeed, double engine) {
        super(brand, maxSpeed);
        this.engineDisplacement = engine;
    }

    @Override
    public void chargeEnergy() {
        System.out.println(getBrand() + "正在加油，发动机排量：" + engineDisplacement + "L");
    }

    public void chargeEnergy(String oilType) {
        System.out.println("使用" + oilType + "型号汽油");
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("类型: 燃油车\n发动机排量: " + engineDisplacement + "L");
    }
}

class VehicleTestProject {
    public static void main(String[] args) {
        System.out.println("===== 交通工具项目启动 =====");
        CarBase[] garage = {
                new ElectricCarImpl("Tesla", 250, 100),
                new GasolineCarImpl("Porsche", 300, 3.0)
        };

        for(CarBase car : garage) {
            car.startEngine();
            car.chargeEnergy();
            car.safetyCheck();

            if(car instanceof GasolineCarImpl) {
                ((GasolineCarImpl)car).chargeEnergy("98#");
            }

            showVehicleInfo(car);
            System.out.println("------------------");
        }
        System.out.println("===== 交通工具项目结束 =====");
    }

    public static void showVehicleInfo(VehicleInterface v) {
        v.showInfo();
    }
}

// 6
abstract class AbstractVehicleBase {
    public abstract void move();
}

class CarExtendsAbstract extends AbstractVehicleBase {
    @Override
    public void move() {
        System.out.println("汽车在公路上行驶（抽象类实现）");
    }
}

class AirplaneExtendsAbstract extends AbstractVehicleBase {
    @Override
    public void move() {
        System.out.println("飞机在天空中飞行（抽象类实现）");
    }
}

class ShipExtendsAbstract extends AbstractVehicleBase {
    @Override
    public void move() {
        System.out.println("轮船在海上航行（抽象类实现）");
    }
}

interface MovableInterface {
    void move();
}

class CarForInterface implements MovableInterface {
    @Override
    public void move() {
        System.out.println("汽车在公路上行驶（接口实现）");
    }
}

class AirplaneForInterface implements MovableInterface {
    @Override
    public void move() {
        System.out.println("飞机在天空中飞行（接口实现）");
    }
}

class ShipForInterface implements MovableInterface {
    @Override
    public void move() {
        System.out.println("轮船在海上航行（接口实现）");
    }
}

class AbstractVsInterfaceDemo {
    public static void main(String[] args) {
        System.out.println("===== 抽象与接口项目启动 =====");
        AbstractVehicleBase car = new CarExtendsAbstract();
        AbstractVehicleBase airplane = new AirplaneExtendsAbstract();
        AbstractVehicleBase ship = new ShipExtendsAbstract();
        car.move();
        airplane.move();
        ship.move();

        MovableInterface movableCar = new CarForInterface();
        MovableInterface movableAirplane = new AirplaneForInterface();
        MovableInterface movableShip = new ShipForInterface();
        movableCar.move();
        movableAirplane.move();
        movableShip.move();

        MovableInterface bike = () -> System.out.println("自行车在骑行（Lambda实现）");
        bike.move();
        System.out.println("===== 抽象与接口项目结束 =====");
    }
}

// 7
class BankInsufficientFundsException extends Exception {
    public BankInsufficientFundsException(String message) {
        super(message);
    }
}

class BankInvalidTransactionException extends Exception {
    public BankInvalidTransactionException(String message) {
        super(message);
    }
}

class BankSystemProjectBank {
    private String name;
    private List<BankAccount> accounts = new ArrayList<>();

    public BankSystemProjectBank(String name) {
        this.name = name;
    }

    public enum AccountType {
        SAVINGS, CURRENT, FIXED_DEPOSIT
    }

    class BankAccount {
        private String id;
        private AccountType type;
        private double balance;
        private BankTransactionListener listener;

        public BankAccount(String id, AccountType type, double balance) {
            this.id = id;
            this.type = type;
            this.balance = balance;

            this.listener = new BankTransactionListener() {
                @Override
                public void onSuccess(double amount, String type) {
                    System.out.printf(" %s 账户 %s %.2f 元\n", id, type, amount);
                }

                @Override
                public void onFailure(String reason) {
                    System.out.printf("操作失败: %s\n", reason);
                }
            };
        }

        public void setTransactionListener(BankTransactionListener listener) {
            this.listener = listener;
        }

        public void deposit(double amount) throws BankInvalidTransactionException {
            if (amount <= 0) {
                throw new BankInvalidTransactionException("存款金额必须大于0");
            }
            balance += amount;
            listener.onSuccess(amount, "存入");
        }

        public void withdraw(double amount) throws BankInsufficientFundsException, BankInvalidTransactionException {
            if (amount <= 0) {
                throw new BankInvalidTransactionException("取款金额必须大于0");
            }
            if (amount > balance) {
                throw new BankInsufficientFundsException(
                        String.format("余额不足 (余额: %.2f, 尝试取款: %.2f)", balance, amount)
                );
            }
            balance -= amount;
            listener.onSuccess(amount, "取出");
        }

        public double getBalance() {
            return balance;
        }
    }

    interface BankTransactionListener {
        void onSuccess(double amount, String type);
        void onFailure(String reason);
    }

    public BankAccount createAccount(String id, AccountType type, double balance) {
        BankAccount acc = new BankAccount(id, type, balance);
        accounts.add(acc);
        return acc;
    }

    public void transfer(BankAccount from, BankAccount to, double amount) {
        class TransactionRecorder {
            void record(String details) {
                System.out.println("转账记录: " + details);
            }
        }

        TransactionRecorder recorder = new TransactionRecorder();

        try {
            from.withdraw(amount);
            to.deposit(amount);
            recorder.record(String.format("%s 向 %s 转账 %.2f 元", from.id, to.id, amount));
        } catch (BankInsufficientFundsException | BankInvalidTransactionException e) {
            recorder.record("转账失败: " + e.getMessage());
            from.listener.onFailure(e.getMessage());
        }
    }
}

class BankSystemProject {
    public static void main(String[] args) {
        System.out.println("===== 银行系统项目启动 =====");
        BankSystemProjectBank icbc = new BankSystemProjectBank("工商银行");

        BankSystemProjectBank.BankAccount alice = icbc.createAccount("A1001",
                BankSystemProjectBank.AccountType.SAVINGS, 1000);
        BankSystemProjectBank.BankAccount bob = icbc.createAccount("A1002",
                BankSystemProjectBank.AccountType.CURRENT, 500);

        alice.setTransactionListener(new BankSystemProjectBank.BankTransactionListener() {
            @Override
            public void onSuccess(double amount, String type) {
                System.out.printf("Alice%s %.2f 元 (余额: %.2f)\n",
                        type, amount, alice.getBalance());
            }

            @Override
            public void onFailure(String reason) {
                System.out.println("Alice交易失败 原因: " + reason);
            }
        });
        try {
            alice.deposit(200);
            alice.withdraw(50);
            bob.deposit(-100);
        } catch (Exception e) {
            System.out.println("操作异常: " + e.getMessage());
        }

        icbc.transfer(alice, bob, 300);
        System.out.println("Alice余额: " + alice.getBalance());
        System.out.println("Bob余额: " + bob.getBalance());


        icbc.transfer(alice, bob, 1000);

        System.out.println("===== 银行系统项目结束 =====");
    }
}