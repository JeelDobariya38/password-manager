import {
  Shield,
  Target,
  Users,
  Zap,
  Lock,
  Code,
  AlertTriangle,
} from "lucide-react";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { Navigation } from "@/components/navigation";

export default function AboutPage() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-background to-muted/20">
      <Navigation />

      <div className="container mx-auto px-4 py-16 max-w-4xl">
        {/* Header */}
        <div className="text-center mb-16">
          <Badge variant="secondary" className="mb-4">
            About Passcodes
          </Badge>
          <h1 className="text-4xl font-bold mb-6">Our Philosophy & Vision</h1>
          <p className="text-xl text-muted-foreground leading-relaxed">
            Password management should be intuitive, secure, and completely
            under your control. Here's what drives us to build Passcodes.
          </p>
        </div>

        {/* Problem Statement */}
        <section className="mb-16">
          <Card>
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <AlertTriangle className="h-6 w-6 text-amber-600" />
                Problem Statement
              </CardTitle>
            </CardHeader>
            <CardContent>
              <p className="text-muted-foreground leading-relaxed">
                In modern days, data is important, especially data that concerns
                authentication information. Storing them securely is a serious
                task (and much of a headache). Passcodes tries to eliminate this
                headache by providing you a simple, sweet and intuitive UI that
                helps you store your passwords in local storage (and to extend
                also in cloud).
                <strong className="text-foreground">
                  {" "}
                  This means you have full control of data you share with the
                  Passcodes app.
                </strong>
              </p>
            </CardContent>
          </Card>
        </section>

        {/* Goals */}
        <section className="mb-16">
          <h2 className="text-3xl font-bold mb-8 text-center">Our Goals</h2>
          <div className="grid md:grid-cols-2 gap-6">
            <Card>
              <CardHeader>
                <Target className="h-8 w-8 text-blue-600 mb-2" />
                <CardTitle>Intuitive Experience</CardTitle>
              </CardHeader>
              <CardContent>
                <p className="text-muted-foreground">
                  Make password management process much more intuitive,
                  enjoyable, customizable & secure by giving you full control
                  over your auth data.
                </p>
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <Zap className="h-8 w-8 text-green-600 mb-2" />
                <CardTitle>Simplify Yet Control</CardTitle>
              </CardHeader>
              <CardContent>
                <p className="text-muted-foreground">
                  Simplify password management while giving extensive control to
                  end users over their authentication workflow.
                </p>
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <Code className="h-8 w-8 text-purple-600 mb-2" />
                <CardTitle>Educational Tool</CardTitle>
              </CardHeader>
              <CardContent>
                <p className="text-muted-foreground">
                  Serve as a guide, reference & extensible tool for people
                  interested in making their password management workflow safer
                  and more customizable.
                </p>
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <Lock className="h-8 w-8 text-red-600 mb-2" />
                <CardTitle>Data Ownership</CardTitle>
              </CardHeader>
              <CardContent>
                <p className="text-muted-foreground">
                  Ensure users maintain complete ownership and control over
                  their sensitive authentication data without vendor lock-in.
                </p>
              </CardContent>
            </Card>
          </div>
        </section>

        {/* Target Users */}
        <section className="mb-16">
          <h2 className="text-3xl font-bold mb-8 text-center">Target Users</h2>
          <div className="space-y-6">
            <Card>
              <CardHeader>
                <div className="flex items-center gap-2">
                  <Badge variant="default">Primary Target</Badge>
                  <CardTitle>Tech-Savvy Enthusiasts</CardTitle>
                </div>
              </CardHeader>
              <CardContent className="space-y-3">
                <p className="text-muted-foreground">
                  <strong>Not So Developer, Not So Normal End-User</strong> -
                  Our primary audience includes:
                </p>
                <ul className="space-y-2 text-muted-foreground">
                  <li className="flex items-start gap-2">
                    <div className="w-2 h-2 bg-blue-600 rounded-full mt-2 flex-shrink-0"></div>
                    Those who wish to optimize their password management
                    workflow but don't know how
                  </li>
                  <li className="flex items-start gap-2">
                    <div className="w-2 h-2 bg-blue-600 rounded-full mt-2 flex-shrink-0"></div>
                    Users willing to spend time learning a specialized password
                    management tool
                  </li>
                  <li className="flex items-start gap-2">
                    <div className="w-2 h-2 bg-blue-600 rounded-full mt-2 flex-shrink-0"></div>
                    People who want control over their auth data without the
                    headache of remembering everything
                  </li>
                  <li className="flex items-start gap-2">
                    <div className="w-2 h-2 bg-blue-600 rounded-full mt-2 flex-shrink-0"></div>
                    Users who don't mind researching documentation to customize
                    their workflow
                  </li>
                </ul>
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <div className="flex items-center gap-2">
                  <Badge variant="secondary">Secondary Target</Badge>
                  <CardTitle>Software Engineers</CardTitle>
                </div>
              </CardHeader>
              <CardContent>
                <p className="text-muted-foreground">
                  The source code is designed to be clean, maintainable, and
                  extensible. Developers can modify the app to meet their
                  specific needs, with comprehensive documentation for learning
                  and extending Passcodes.
                </p>
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <div className="flex items-center gap-2">
                  <Badge variant="outline">End Users</Badge>
                  <CardTitle>General Users</CardTitle>
                </div>
              </CardHeader>
              <CardContent>
                <p className="text-muted-foreground">
                  While we support general users, the app is optimized for
                  customization rather than simplicity. If you're a general
                  user, consider this a starting point for your password
                  management journey. We recommend evolving into our primary
                  target group to use the app more effectively.
                </p>
              </CardContent>
            </Card>
          </div>
        </section>

        {/* Priorities */}
        <section className="mb-16">
          <h2 className="text-3xl font-bold mb-8 text-center">
            Our Priorities
          </h2>

          <Alert className="mb-6 border-blue-200 bg-blue-50 dark:border-blue-800 dark:bg-blue-950">
            <Zap className="h-4 w-4 text-blue-600 dark:text-blue-400" />
            <AlertTitle className="text-blue-800 dark:text-blue-200">
              Priority #1: Customization & Extensibility
            </AlertTitle>
            <AlertDescription className="text-blue-700 dark:text-blue-300">
              The app will be fully extensible & customizable without being
              limited by abstraction and simplification. We prioritize making
              internals abstract and decoupled, even if it means losing some
              simplicity in the UI/UX.
            </AlertDescription>
          </Alert>

          <Alert className="mb-6 border-green-200 bg-green-50 dark:border-green-800 dark:bg-green-950">
            <Shield className="h-4 w-4 text-green-600 dark:text-green-400" />
            <AlertTitle className="text-green-800 dark:text-green-200">
              Priority #2: Security & Robustness
            </AlertTitle>
            <AlertDescription className="text-green-700 dark:text-green-300">
              The app includes multiple redundant software checks for data and
              user input to ensure robust operation. These checks might add
              performance overhead, but security is more important than
              performance for password management.
            </AlertDescription>
          </Alert>

          <Alert className="border-gray-200 bg-gray-50 dark:border-gray-800 dark:bg-gray-950">
            <AlertTriangle className="h-4 w-4 text-gray-600 dark:text-gray-400" />
            <AlertTitle className="text-gray-800 dark:text-gray-200">
              Performance is Not Our Priority
            </AlertTitle>
            <AlertDescription className="text-gray-700 dark:text-gray-300">
              We believe users can wait 2-3 minutes if the app is doing
              redundant checks to ensure data validity. However, if performance
              becomes a serious issue (like taking 1 minute for a simple
              password storage action), we will address it.
            </AlertDescription>
          </Alert>
        </section>

        {/* Philosophy */}
        <section>
          <Card>
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <Users className="h-6 w-6 text-purple-600" />
                What We Think of Password Management
              </CardTitle>
            </CardHeader>
            <CardContent>
              <p className="text-muted-foreground leading-relaxed">
                Password management is something as simple as remembering a
                password, yet it's a very important topic in terms of security.
                As developers of Passcodes, we aim to streamline and secure the
                process as much as possible while giving users complete control
                over their data and workflow.
              </p>
              <p className="text-muted-foreground leading-relaxed mt-4">
                We believe that true security comes from transparency, user
                control, and the ability to customize your tools to fit your
                specific needs rather than adapting to rigid, one-size-fits-all
                solutions.
              </p>
            </CardContent>
          </Card>
        </section>
      </div>
    </div>
  );
}
