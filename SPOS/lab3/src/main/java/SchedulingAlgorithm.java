// Run() is called from Scheduling.main() and is where
// the scheduling algorithm written by the user resides.
// User modification should occur within the Run() function.

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.util.Queue;

public class SchedulingAlgorithm {

    public static sProcess getProcess(List<sProcess> processVector, Queue<Integer> usersQueue, int currentUser, boolean sameUser, sProcess exceptProcess) {
        sProcess result = null;

        while (true) {
            if (!sameUser) {
                currentUser = usersQueue.remove();
                usersQueue.add(currentUser);
            }

            for (sProcess p : processVector) {
                if (sameUser) {
                    if (currentUser == p.userID && p.cpudone != p.cputime && (exceptProcess == null || (p != exceptProcess))) {
                        result = p;
                        return result;
                    }
                } else {
                    if (currentUser == p.userID && p.cpudone != p.cputime) {
                        result = p;
                        return result;
                    }
                }
            }

            if (sameUser) {
                sameUser = false;
            }
        }
    }

    private static final long startTime = System.currentTimeMillis();

    private static void printTime(PrintStream out) {
        out.print("Time: " + (System.currentTimeMillis() - startTime) + " ");
    }

    private static void printRegistered(PrintStream out, sProcess process, int processIndex) {
        printTime(out);
        out.println("User: " + process.userID + " " + "Process: " + processIndex + " registered... (" + process.cputime + " " + process.ioblocking + " " + process.cpudone + ")");
    }

    private static void printUserSwitched(PrintStream out, boolean switched, sProcess process) {
        printTime(out);

        if (switched) {
            out.println("User: " + process.userID + " " + "User switched");
        } else {
            out.println("User: " + process.userID + " " + "Switching user failed");
        }
    }

    public static Results run(int runtime, String logsFile, List<sProcess> processVector, Results result, int quantum) {
        int comptime = 0;
        int size = processVector.size();
        int completed = 0;

        HashSet<Integer> users = new HashSet<>();
        for (sProcess p : processVector) {
            users.add(p.userID);
        }

        Queue<Integer> usersQueue = new LinkedList<>(users);

        result.schedulingType = "Interactive (Preemptive)";
        result.schedulingName = "Fair share";

        int quantumRemainingTime = 0;
        try {
            PrintStream out = new PrintStream(new FileOutputStream(logsFile));
            sProcess process = processVector.get(0);
            usersQueue.remove(process.userID);
            usersQueue.add(process.userID);
            printRegistered(out, process, processVector.indexOf(process));
            while (comptime < runtime) {
                if (process.cpudone == process.cputime) {
                    completed++;
                    out.print("Time: " + (System.currentTimeMillis() - startTime) + " ");
                    out.println("User: " + process.userID + " " + "Process: " + processVector.indexOf(process) + " completed... (" + process.cputime + " " + process.ioblocking + " " + process.cpudone + ")");
                    if (completed == size) {
                        result.compuTime = comptime;
                        out.close();
                        return result;
                    }

                    sProcess previousProcess = process;

                    if (quantumRemainingTime == quantum) {
                        quantumRemainingTime = 0;
                        out.print("Time: " + (System.currentTimeMillis() - startTime));
                        out.println(" User: " + process.userID + " " + "Process: " + processVector.indexOf(process) + " quantum expired, switching user... (" + process.cputime + " " + process.ioblocking + " " + process.cpudone + ")");
                        process = getProcess(processVector, usersQueue, process.userID, false, null);

                        if (process.userID == previousProcess.userID) {
                            printUserSwitched(out, false, process);
                        }
                    } else {
                        process = getProcess(processVector, usersQueue, process.userID, true, null);
                    }

                    if (process.userID != previousProcess.userID) {
                        printUserSwitched(out, true, process);
                    }

                    printRegistered(out, process, processVector.indexOf(process));
                }
                if (process.ioblocking > 0 && process.ioblocking == process.ionext) {
                    out.print("Time: " + (System.currentTimeMillis() - startTime) + " ");
                    out.println("User: " + process.userID + " " + "Process: " + processVector.indexOf(process) + " I/O blocked... (" + process.cputime + " " + process.ioblocking + " " + process.cpudone + ")");
                    process.numblocked++;
                    process.ionext = 0;

                    sProcess previousProcess = process;

                    if (quantumRemainingTime == quantum) {
                        quantumRemainingTime = 0;
                        process = getProcess(processVector, usersQueue, process.userID, false, null);

                        if (process.userID == previousProcess.userID) {
                            printUserSwitched(out,false,process);
                        }
                    } else {
                        Queue<Integer> usersQueueCopy = new LinkedList<>(usersQueue);
                        process = getProcess(processVector, usersQueue, process.userID, true, process);

                        if (previousProcess.userID != process.userID) {
                            process = previousProcess;
                            usersQueue = usersQueueCopy;
                        }
                    }

                    if (process.userID != previousProcess.userID) {
                        printUserSwitched(out, true, process);
                    }

                    printRegistered(out, process, processVector.indexOf(process));
                }

                if (quantumRemainingTime == quantum) {
                    quantumRemainingTime = 0;
                    sProcess previousProcess = process;
                    out.print("Time: " + (System.currentTimeMillis() - startTime) + " ");
                    out.println("User: " + process.userID + " " + "Process: " + processVector.indexOf(process) + " quantum expired, switching user... (" + process.cputime + " " + process.ioblocking + " " + process.cpudone + ")");
                    process = getProcess(processVector, usersQueue, process.userID, false, null);

                    printUserSwitched(out,previousProcess.userID != process.userID,process);

                    printRegistered(out, process, processVector.indexOf(process));
                }

                process.cpudone++;
                if (process.ioblocking > 0) {
                    process.ionext++;
                }
                comptime++;
                quantumRemainingTime++;
            }
            out.close();
        } catch (IOException e) { /* Handle exceptions */ }
        result.compuTime = comptime;
        return result;
    }
}
