/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.pozoga.nice.classes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/*
 * Bild omment:
 *
 * Klasa powinna reprezenować wszystkie klasy
 */


/**
 * Main module class. Use to access to classes system.
 * 
 * @author Sebastian Pożoga
 */
public class C {
    
    /**
     * Return Pack of all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @return The class description and access object
     */
    protected static Map<Class, ClassDesc> classDescs = new HashMap<Class, ClassDesc>();
    public static ClassDesc get(Class c) throws Exception {
        ClassDesc desc = classDescs.get(c);
        if (desc == null) {
            desc = new ClassDesc(c);
            classDescs.put(c, desc);
        }
        return desc;
    }
    
    public static void register(Class c) throws Exception{
        get().add(c);
    }
    
    public static void unregister(Class c) throws Exception{
        get().remove(c);
    }
    
    /**
     * Return Pack of all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @return The classes
     */
    private static ClassPack classPack = null;
    public static ClassPack get() throws Exception {
        if (classPack == null) {
            classPack = new ClassPack(getClasses(""));
        }
        return classPack;
    }

    public static ClassPack select(PackFilter filter) throws Exception {
        return get().select(filter);
    }


    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    protected static Collection<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        Collection<Class> classes = new HashSet<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    protected static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                String findPackage = ((packageName!=null && !packageName.equals("")) ? packageName+ "." :packageName)  + file.getName();
                classes.addAll(findClasses(file, findPackage));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

}
