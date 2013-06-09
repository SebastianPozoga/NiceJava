package eu.pozoga.nice.classes;

/**
 * Main module class. Use to access to classes system.
 *
 * @author Sebastian Pożoga
 */
public class C {

    protected ClassPack mainPack = null;
    protected static C instance = null;

    public C() throws Exception {
        init();
    }

    public void init() throws Exception {
        JavaClasses jClasses = new JavaClasses();
        mainPack = new ClassPack(jClasses.getClasses(""));
    }

    /**
     * Return Pack of all classes accessible from the context class loader which
     * belong to the given package and subpackages.
     *
     * @return The classes
     */
    public ClassPack getPack() throws Exception {
        return mainPack;
    }

    /**
     * Select subpackage by filter
     * 
     * @param filter
     * @return ClassPack
     * @throws Exception 
     */
    public ClassPack select(PackFilter filter) throws Exception {
        return getPack().select(filter);
    }

    /**
     * Scans all classes accessible from the context class loader which belong
     * to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException / protected static Collection<Class>
     * getClasses(String packageName) throws ClassNotFoundException, IOException
     * { ClassLoader classLoader =
     * Thread.currentThread().getContextClassLoader(); assert classLoader !=
     * null; String path = packageName.replace('.', '/'); Enumeration<URL>
     * resources = classLoader.getResources(path); List<File> dirs = new
     * ArrayList<File>(); while (resources.hasMoreElements()) { URL resource =
     * resources.nextElement(); dirs.add(new File(resource.getFile())); }
     * Collection<Class> classes = new HashSet<Class>(); for (File directory :
     * dirs) { classes.addAll(findClasses(directory, packageName)); } return
     * classes; }
     *
     * /**
     * Recursive method used to find all classes in a given directory and
     * subdirs.
     *
     * @param directory The base directory
     * @param packageName The package name for classes found inside the base
     * directory
     * @return The classes
     * @throws ClassNotFoundException / protected static List<Class>
     * findClasses(File directory, String packageName) throws
     * ClassNotFoundException { List<Class> classes = new ArrayList<Class>(); if
     * (!directory.exists()) { return classes; } File[] files =
     * directory.listFiles(); for (File file : files) { if (file.isDirectory())
     * { assert !file.getName().contains("."); String findPackage =
     * ((packageName!=null && !packageName.equals("")) ? packageName+ "."
     * :packageName) + file.getName(); classes.addAll(findClasses(file,
     * findPackage)); } else if (file.getName().endsWith(".class")) {
     * classes.add(Class.forName(packageName + '.' + file.getName().substring(0,
     * file.getName().length() - 6))); } } return classes;
    }
     */
    public static C getInstance() throws Exception {
        if (instance == null) {
            instance = new C();
        }
        return instance;
    }
}
