package eu.pozoga.nice.classes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use to find java classes. Search by package name and resource
 */
public class JavaClasses {
    
    ClassLoader classLoader;

    public JavaClasses(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
            
    public JavaClasses() throws Exception {
        classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null){
            throw new Exception("Thread ContextClassLoader: No found");
        }
    }
    
    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public Collection<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException, Exception {
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
    protected List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if(file.getName().contains(".")){
                    continue;
                }
                String findPackage = ((packageName!=null && !packageName.equals("")) ? packageName+ "." :packageName)  + file.getName();
                classes.addAll(findClasses(file, findPackage));
            } else if (file.getName().endsWith(".class")) {
                try{
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }catch(ClassNotFoundException ex){
                    Logger.getLogger(C.class.getName()).log(Level.SEVERE, "No load class: "+C.class.getName(), ex);
                }
            }
        }
        return classes;
    }

    
}
