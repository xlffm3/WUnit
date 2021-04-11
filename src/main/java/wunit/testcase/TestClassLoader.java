package wunit.testcase;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TestClassLoader {
    private static final String TEST_CLASS_NOT_FOUND = "There are not test classes under specified package name.";

    private TestClassLoader() {
    }

    public static List<Class<?>> loadTestClasses(String testPackageRootName) throws ClassNotFoundException {
        ClassLoader classLoader = Objects.requireNonNull(TestClassLoader.class.getClassLoader());
        String testPackageRootPath = testPackageRootName.replace('.', '/');
        URL resource = Objects.requireNonNull(classLoader.getResource(testPackageRootPath), TEST_CLASS_NOT_FOUND);
        File testPackageRootDirectory = new File(resource.getFile());
        return findTestClasses(testPackageRootDirectory, testPackageRootName);
    }

    private static List<Class<?>> findTestClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            examineFileAttribute(file, classes, packageName);
        }
        return classes;
    }

    private static void examineFileAttribute(File file, List<Class<?>> classes, String packageName) throws ClassNotFoundException {
        if (file.isDirectory()) {
            classes.addAll(findTestClasses(file, packageName + "." + file.getName()));
            return;
        }
        if (file.getName().endsWith(".class")) {
            String className = parseClassName(file, packageName);
            classes.add(Class.forName(className));
        }
    }

    private static String parseClassName(File file, String packageName) {
        String classFullName = file.getName();
        String classSimpleName = classFullName.substring(0, classFullName.length() - 6);
        return packageName + "." + classSimpleName;
    }
}
