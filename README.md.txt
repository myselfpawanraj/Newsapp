#include <iostream>

using namespace std;
int main()
{   int n,x,y;
    cin >> n;
    int a[n];
    int g[n][n];

for (int i = 0; i < n; i++)
    {   
        cin>>a[i];
        if(i==0)
        x=a[0];
        if(i==n-1)
        y=a[n-1];
        for(int j=0;j<n;j++ )
        {
        g[i][n-1-j]=a[i]%10;
        a[i]=a[i]/10;
        }
    }

    
for (int i = 0; i < n; i++)
{   
    if(i==0)
    cout<<x<<endl;
    else if(i==n-1)
    cout<<y;    
    else {
        cout<<g[i][0];
        for(int j=1;j<n-1;j++)
           {
            if(g[i][j]>g[i+1][j]&&g[i][j]>g[i][j+1]&&g[i][j]>g[i-1][j]&&g[i][j]>g[i][j-1])
            cout<<"X";        
            else
            cout<<g[i][j];        
           }
        cout<<g[i][n-1]<<endl;
        }
    
}

return 0;
}
