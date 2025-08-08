import {
  Shield,
  Smartphone,
  Code,
  Users,
  AlertTriangle,
  Lock,
  Eye,
  Download,
} from "lucide-react";
import { Button } from "@/components/ui/button";
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
import Link from "next/link";

export default function HomePage() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-background to-muted/20">
      <Navigation />

      {/* Hero Section */}
      <section className="py-20 px-4">
        <div className="container mx-auto text-center max-w-4xl">
          <Badge variant="secondary" className="mb-4">
            Open Source • Under Development
          </Badge>
          <h2 className="text-5xl font-bold mb-6">
            Take Down the Headache of Remembering Passwords
          </h2>
          <p className="text-xl text-muted-foreground mb-8 leading-relaxed">
            An open source Android app that helps you keep your passwords safe
            and secure in your own local storage without ever needing to push
            them to the cloud.
          </p>

          {/* Warning Alert */}
          <Alert className="mb-8 max-w-2xl mx-auto border-amber-200 bg-amber-50 dark:border-amber-800 dark:bg-amber-950">
            <AlertTriangle className="h-4 w-4 text-amber-600 dark:text-amber-400" />
            <AlertTitle className="text-amber-800 dark:text-amber-200">
              Development Warning
            </AlertTitle>
            <AlertDescription className="text-amber-700 dark:text-amber-300">
              This is an open source project currently under active development.
              Please consider using it for fun and not for real password
              management until we officially release a stable version.
            </AlertDescription>
          </Alert>

          <div className="flex flex-col sm:flex-row gap-4 justify-center">
            <Button size="lg" className="gap-2">
              <Download className="h-5 w-5" />
              Download APK
            </Button>
            <Button variant="outline" size="lg" className="gap-2" asChild>
              <Link href="/github">
                <Code className="h-5 w-5" />
                View Source Code
              </Link>
            </Button>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section id="features" className="py-16 px-4 bg-muted/30">
        <div className="container mx-auto">
          <h3 className="text-3xl font-bold text-center mb-12">
            Why Choose Passcodes?
          </h3>
          <div className="grid md:grid-cols-3 gap-8">
            <Card>
              <CardHeader>
                <Shield className="h-10 w-10 text-blue-600 mb-2" />
                <CardTitle>Local Storage First</CardTitle>
                <CardDescription>
                  Your passwords stay on your device. Full control over your
                  sensitive data.
                </CardDescription>
              </CardHeader>
            </Card>

            <Card>
              <CardHeader>
                <Code className="h-10 w-10 text-green-600 mb-2" />
                <CardTitle>Open Source</CardTitle>
                <CardDescription>
                  Transparent, auditable code. Contribute, customize, and extend
                  as needed.
                </CardDescription>
              </CardHeader>
            </Card>

            <Card>
              <CardHeader>
                <Lock className="h-10 w-10 text-purple-600 mb-2" />
                <CardTitle>Highly Customizable</CardTitle>
                <CardDescription>
                  Extensible architecture designed for power users who want
                  control.
                </CardDescription>
              </CardHeader>
            </Card>
          </div>
        </div>
      </section>

      {/* Compatibility Section */}
      <section id="compatibility" className="py-16 px-4">
        <div className="container mx-auto max-w-4xl">
          <h3 className="text-3xl font-bold text-center mb-12">
            Compatibility
          </h3>
          <Card>
            <CardHeader>
              <div className="flex items-center gap-2">
                <Smartphone className="h-6 w-6 text-green-600" />
                <CardTitle>Android Requirements</CardTitle>
              </div>
            </CardHeader>
            <CardContent className="space-y-4">
              <div className="grid sm:grid-cols-2 gap-4">
                <div>
                  <h4 className="font-semibold mb-2">Compile SDK</h4>
                  <p className="text-muted-foreground">
                    Android 16 (API level 36)
                  </p>
                </div>
                <div>
                  <h4 className="font-semibold mb-2">Minimum Version</h4>
                  <p className="text-muted-foreground">
                    Android 8+ (API level 26+)
                  </p>
                </div>
                <div>
                  <h4 className="font-semibold mb-2">Officially Supported</h4>
                  <p className="text-muted-foreground">
                    Android 14 (API level 34)
                  </p>
                </div>
                <div>
                  <h4 className="font-semibold mb-2">Higher Versions</h4>
                  <p className="text-muted-foreground">
                    Can run but not officially guaranteed
                  </p>
                </div>
              </div>
            </CardContent>
          </Card>
        </div>
      </section>

      {/* Target Users Section */}
      <section className="py-16 px-4 bg-muted/30">
        <div className="container mx-auto max-w-4xl">
          <h3 className="text-3xl font-bold text-center mb-12">
            Who Is This For?
          </h3>
          <div className="space-y-6">
            <Card>
              <CardHeader>
                <div className="flex items-center gap-2">
                  <Badge variant="default">Primary Target</Badge>
                  <CardTitle>Tech-Savvy Users</CardTitle>
                </div>
                <CardDescription>
                  For those who want to optimize their password management
                  workflow and are willing to learn and customize.
                </CardDescription>
              </CardHeader>
            </Card>

            <Card>
              <CardHeader>
                <div className="flex items-center gap-2">
                  <Badge variant="secondary">Secondary Target</Badge>
                  <CardTitle>Software Engineers</CardTitle>
                </div>
                <CardDescription>
                  Clean, maintainable, and extensible codebase for developers
                  who want to modify and extend the app.
                </CardDescription>
              </CardHeader>
            </Card>

            <Card>
              <CardHeader>
                <div className="flex items-center gap-2">
                  <Badge variant="outline">End Users</Badge>
                  <CardTitle>General Users</CardTitle>
                </div>
                <CardDescription>
                  While supported, the app is optimized for customization rather
                  than simplicity. Consider this a starting point for your
                  password management journey.
                </CardDescription>
              </CardHeader>
            </Card>
          </div>
        </div>
      </section>

      {/* Security Section */}
      <section id="security" className="py-16 px-4">
        <div className="container mx-auto max-w-4xl">
          <h3 className="text-3xl font-bold text-center mb-12">
            Security Considerations
          </h3>

          <Alert className="mb-8 border-red-200 bg-red-50 dark:border-red-800 dark:bg-red-950">
            <AlertTriangle className="h-4 w-4 text-red-600 dark:text-red-400" />
            <AlertTitle className="text-red-800 dark:text-red-200">
              Important Security Notice
            </AlertTitle>
            <AlertDescription className="text-red-700 dark:text-red-300">
              Passcodes is an open source project under MIT License, provided
              "as is" without warranty. Your passwords are as safe as writing
              them on paper or in a text file. We recommend waiting for the
              stable release before trusting it with real passwords.
            </AlertDescription>
          </Alert>

          <div className="grid md:grid-cols-2 gap-8">
            <Card>
              <CardHeader>
                <CardTitle className="flex items-center gap-2">
                  <Eye className="h-5 w-5 text-blue-600" />
                  Security Recommendations
                </CardTitle>
              </CardHeader>
              <CardContent className="space-y-3">
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-blue-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    Lock the app using your phone's settings
                  </p>
                </div>
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-blue-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    Use a different password than your lock screen
                  </p>
                </div>
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-blue-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    Hide the app if you don't use it often
                  </p>
                </div>
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <CardTitle className="flex items-center gap-2">
                  <Shield className="h-5 w-5 text-green-600" />
                  Our Priorities
                </CardTitle>
              </CardHeader>
              <CardContent className="space-y-3">
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-green-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    <strong>1st Priority:</strong> Customization & Extensibility
                  </p>
                </div>
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-green-600 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    <strong>2nd Priority:</strong> Security & Robustness
                  </p>
                </div>
                <div className="flex items-start gap-2">
                  <div className="w-2 h-2 bg-gray-400 rounded-full mt-2 flex-shrink-0"></div>
                  <p className="text-muted-foreground">
                    Performance is not our main focus
                  </p>
                </div>
              </CardContent>
            </Card>
          </div>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-muted py-12 px-4">
        <div className="container mx-auto text-center">
          <div className="flex items-center justify-center gap-2 mb-4">
            <Shield className="h-6 w-6" />
            <h4 className="text-xl font-bold">Passcodes</h4>
          </div>
          <p className="text-muted-foreground mb-6">
            Open source password management for Android. Built by developers,
            for developers.
          </p>
          <div className="flex justify-center gap-6">
            <Button variant="ghost" asChild>
              <Link href="/github">GitHub</Link>
            </Button>
            <Button variant="ghost" asChild>
              <Link href="/about">About</Link>
            </Button>
            <Button variant="ghost">Documentation</Button>
          </div>
          <div className="mt-8 pt-8 border-t text-muted-foreground text-sm">
            <p>
              Licensed under MIT License. Use at your own risk during
              development phase.
            </p>
          </div>
        </div>
      </footer>
    </div>
  );
}
